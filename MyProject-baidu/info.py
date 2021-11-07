#输出推理模型
import paddle
import paddle.inference as paddle_infer
from paddle.static import InputSpec
from paddle.jit import to_static
from models.SeResNeXt import SeResNeXt
from paddle.vision.transforms import *
import numpy as np
from config import config

class Info():#推理类
    def __init__(self): # 初始化推理引擎
        #模型推理部分
        cf = paddle_infer.Config(config['info_save_path']+'.pdmodel', config['info_save_path']+'.pdiparams')
        cf.disable_gpu()
        self.predictor = paddle_infer.create_predictor(cf)  # 创建执行引擎

        self.input_names = self.predictor.get_input_names()
        self.input_handle = self.predictor.get_input_handle(self.input_names[0])
    def predict(self,img):
        trans = Compose([Resize(config['input_size']),Normalize(mean=config['mean'],std=config['std'],data_format='HWC')])
        img = trans(img).astype(np.float32)
        img = img.transpose((2, 0, 1))
        img = np.expand_dims(img, 0)

        self.input_handle.copy_from_cpu(img)
        self.predictor.run()

        output_names = self.predictor.get_output_names()
        output_handle = self.predictor.get_output_handle(output_names[0])
        output_data = output_handle.copy_to_cpu()
        output_data = output_data.squeeze()
        output_data = np.exp(output_data)
        output_data = output_data/np.sum(output_data)
        return int(np.argmax(output_data)),float(np.max(output_data))#返回值为对应分类下标以及其概率值

    def model_to_info(self): #该方法完成动专静的模型减枝
        #加载模型
        model = SeResNeXt(layers=50,class_dim=config['class_dim'])  # 实例化模型
        parameters_dict = paddle.load(config['train_res_paramsPath'])
        model.set_state_dict(parameters_dict)

        #需要先将前面的动态模型 完成动专静的模型操作保存
        net = to_static(model,
                        input_spec=[InputSpec(shape=[None,3,224,224],name='x')])
        paddle.jit.save(net,'infoResult/SeResNeXt')
