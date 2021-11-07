import paddle
from paddle.io import Dataset
import os
from PIL import Image
import numpy as np
from paddle.vision.transforms import *
import paddle.fluid as fluid
from config import config

class MyData(Dataset):
    def __init__(self,name,flag):  # 需要重写__init__ 、 __get__item 、__len__方法
        # 这个初始化方法需要将要加载的数据集全部加载进下来
        self.flag = flag
        self.base_path = 'data/wheat/'
        self.data = []
        self.readFile(name)

        self.train_transform = Compose([
            Resize([224,224]),
            ColorJitter(brightness=config['brightness'], contrast=config['contrast'], saturation=config['saturation'], hue=config['hue']),
            RandomRotation(config['degree']),
            RandomHorizontalFlip(config['p']),
            Normalize(mean=config['mean'], std=config['std'], data_format='HWC')]
        )
        self.val_trainsform = Compose(
            [Resize([224,224]),
            Normalize(mean=config['mean'], std=config['std'], data_format='HWC')]
        )

    def __getitem__(self, idx):  # 这个方法需要重写 将其每一张照片路径获取返回
        item = self.data[idx].split(" ")  # 获取其中一张数据集
        item_img = paddle.dataset.image.load_image(self.base_path+item[0])
        if(self.flag == "train"):
            item_img = self.train_transform(item_img)
        else:item_img = self.val_trainsform(item_img)
        item_img = np.array(item_img, dtype='float32')
        item_img = item_img.transpose((2, 0, 1))
        label = np.array([int(item[1])]).astype(np.int64)
        return item_img, label  # 返回对应一个数据 以及它的label
    def __len__(self):  # 该方法返回数据集总长度
        return len(self.data)
    def readFile(self,name):
        f = open(name)  # 返回一个文件对象
        line = f.readline()  # 调用文件的 readline()方法
        self.data.append(line)
        while line:
            line = f.readline()
            if line != "":
                self.data.append(line)
        f.close()


### 该函数返回训练集与验证集的dataloader
def getDataLoader(flag):
    if flag == "train":
        return paddle.io.DataLoader(MyData(config['train_path'],"train"), batch_size=config['train_batch_size'], shuffle=True)
    else:return paddle.io.DataLoader(MyData(config['val_path'],"valid"), batch_size=config['val_batch_size'], shuffle=False)


