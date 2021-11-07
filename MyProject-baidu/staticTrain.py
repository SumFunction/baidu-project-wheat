import getData
import paddle
import json
from models import SeResNeXt
import sys
import os
import paddle.fluid as fluid
import getOptimizer
import time
import numpy as np
import yaml
from visualdl import LogWriter
config = {}
paddle.enable_static()#开启静态图模式
def loadConfig():#加载配置文件
    global config
    path = r"./config.yaml"
    with open(path,'r',encoding='utf-8') as f:
        config = f.read()
        config = yaml.safe_load(config)
def train():
    # 定义输入
    inputs = fluid.data(name='inputs', shape=[-1] + config['input_size'], dtype='float32')
    label = fluid.data(name='label', shape=[-1, 1], dtype='int64')

    # 数据集的构造
    train_dataset, val_dataset = getData.getDataSet(config['train_path'],config['val_path'])
    train_loader = paddle.io.DataLoader(train_dataset, batch_size=config['train_batch_size'], shuffle=True, feed_list=[inputs, label])
    val_loader = paddle.io.DataLoader(val_dataset, batch_size=config['val_batch_size'], shuffle=False, feed_list=[inputs, label])


    # 模型获取
    model = SeResNeXt.SE_ResNeXt50_vd_32x4d()
    prediction = model.net(inputs, config["class_dim"])
    acc_top1 = fluid.layers.accuracy(input=prediction, label=label, k=1)
    loss = paddle.nn.functional.cross_entropy(input=prediction, label=label)
    avg_loss = fluid.layers.mean(x=loss)



    #恢复训练，重新加载模型以及对应优化器
    #是否使用之前训练的模型
    use_predtrain = True
    if use_predtrain:

        #优化器需要重新初始化
        scheduler = paddle.optimizer.lr.NaturalExpDecay(learning_rate=0.1, gamma=0.1, verbose=True)
        adam = paddle.optimizer.Adam(learning_rate=scheduler)
        adam.minimize(avg_loss)
        #重新加载模型
        # load program
        prog = paddle.load(config["train_save_path"] + '.pdmodel')

        state_dict_param = paddle.load(config["train_save_path"] + '.pdparams')
        prog.set_state_dict(state_dict_param)
        state_dict_opt = paddle.load(config["train_save_path"] + '.pdopt')
        prog.set_state_dict(state_dict_opt)
    else:
        scheduler = paddle.optimizer.lr.NaturalExpDecay(learning_rate=config['lr'], gamma=0.1, verbose=True)
        adam = paddle.optimizer.Adam(learning_rate=scheduler)
        adam.minimize(avg_loss)
        prog = paddle.static.default_main_program()

    #创建GPU
    place = fluid.CUDAPlace(0) if config['use_gpu'] else fluid.CPUPlace()
    #创建执行器
    exe = paddle.static.Executor(place)
    exe.run(paddle.static.default_startup_program())


    train_fetch_list = [avg_loss.name, acc_top1.name, prediction.name]


    # 主体训练过程
    bestAcc = 0
    with LogWriter(logdir="./log/train1") as writer:
        for epoch in range(config['num_epochs']):
            # if epoch == 0 and use_predtrain == True:#恢复训练
            #     epoch = other_res['epoch'] + 1
            total = 0
            each_acc = 0
            cnt = 0
            for batch_id, (x_tensor, y_tensor) in enumerate(train_loader):
                # 区别7：静态图中需要调用执行器的run方法执行计算过程，需要获取的计算结果（如avg_loss）需要通过fetch_list指定
                loss_value, acc, pred = exe.run(feed={'inputs': x_tensor, 'label': y_tensor}, fetch_list=train_fetch_list)

                each_acc += acc
                total += loss_value
                cnt = batch_id
            each_acc /= (cnt + 1)
            if bestAcc <= each_acc:
                bestAcc = each_acc[0]
            print("{}次迭代：损失{}，精度{}".format(epoch, total, each_acc))
            writer.add_scalar(tag="accuracy", step=epoch+1, value=each_acc)
            writer.add_scalar(tag="loss", step=epoch+1, value=total)
            scheduler.step()
            if epoch >= 20 and bestAcc <= each_acc:
                # 保存模型，模型参数
                paddle.static.save(prog, config["train_save_path"])
                #保存迭代步数以及当前学习率信息
                other_res = {"epoch": epoch,"lr": scheduler.get_lr(),"bestAcc": str(bestAcc)}
                with open(config["train_other_path"], 'w') as file_obj:
                    json.dump(other_res, file_obj)

loadConfig()
train()







