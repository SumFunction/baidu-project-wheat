import paddle.fluid as fluid
import numpy as np
import paddle
import getData
from models.SeResNeXt import SeResNeXt
from config import config
from visualdl import LogWriter
def loadOptimizer(parameters):
    scheduler =  paddle.optimizer.lr.CosineAnnealingDecay(learning_rate=config['lr'],
                                                          T_max=config['num_epochs'],
                                                          eta_min=config['min_lr'],
                                                          verbose=True)
    adam = paddle.optimizer.Adam(learning_rate=scheduler,parameters=parameters)
    return adam,scheduler
def train():
    train_loader = getData.getDataLoader("train")
    model = SeResNeXt(layers=50,class_dim=config['class_dim']) #实例化模型
    parameters_dict = paddle.load(config['train_res_paramsPath'])
    model.set_state_dict(parameters_dict)

    optimizer,scheduler = loadOptimizer(model.parameters()) #实例化优化器
    # parameters_dict = paddle.load('trainResult/model.pdopt')
    # optimizer.set_state_dict(parameters_dict)
    num_epochs = config['num_epochs']

    # 执行epoch_num次训练
    with fluid.dygraph.guard():
        bestAcc = 0
        with LogWriter(logdir="./log/train1") as writer:
            for epoch in range(num_epochs):
                # 读取训练数据进行训练
                batch_loss = 0
                batch_acc = 0
                batch = 0
                for batch_id,(inputs,labels) in enumerate(train_loader):
                    batch += 1
                    # 网络正向执行
                    pred, acc = model(inputs, labels)

                    # 计算损失值
                    loss = paddle.nn.functional.cross_entropy(pred, labels)
                    avg_loss = paddle.mean(loss)
                    # 执行反向计算
                    avg_loss.backward()
                    # 参数更新
                    optimizer.step()
                    # 将本次计算的梯度值清零，以便进行下一次迭代和梯度更新
                    optimizer.clear_grad()

                    batch_loss += avg_loss
                    batch_acc += acc
                batch_acc /= batch
                if bestAcc <= batch_acc:
                    print("成功保存,第{}代迭代,精度为:{}".format(epoch,batch_acc))
                    bestAcc = batch_acc
                    paddle.save(model.state_dict(), config['train_res_paramsPath'])
                    paddle.save(optimizer.state_dict(), config['train_res_pdoptPath'])
                print("Epoch {},Loss {},Accuracy {}".format(epoch, np.array(batch_loss)[0], np.array(batch_acc)[0]))
                scheduler.step()
                writer.add_scalar(tag="Accuracy", step=epoch+1, value=batch_acc)
                writer.add_scalar(tag="Loss", step=epoch+1, value=batch_loss)
                writer.add_scalar(tag="Learning_rate", step=epoch + 1, value=optimizer.get_lr())



if __name__ == "__main__":
    train()