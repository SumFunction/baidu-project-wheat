"""
此文件作用，计算验证集模型精度
"""
import paddle
import getData
from config import config
from models.SeResNeXt import SeResNeXt
def calAcc(model):#可以外部调用它 传入模型自动评估 也可以自己在下面实现完成评估
    model.eval()
    val_data_loader = getData.getDataLoader("val")
    cnt = 0
    total_acc = 0
    for index,(inputs,lables) in enumerate(val_data_loader):
        pred,acc = model(inputs,lables)
        total_acc += acc
        cnt += 1
    print("验证集的模型精度为：{}".format(total_acc/(cnt)))

model = SeResNeXt(layers=50,class_dim=config["class_dim"])
model.set_state_dict(paddle.load(config['train_res_paramsPath']))
calAcc(model)


