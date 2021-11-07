##微服务端口
import os
from flask import *
import datetime
from flask_cors import CORS,cross_origin
import paddle
import numpy as np
from info import Info #推理模型

app = Flask(__name__)
cors = CORS(app)
@cross_origin()
@app.route('/uploadResnet', methods=['GET', 'POST'])
def uoloadResnet():#小麦病害分类接收接口
    file = request.files['file']#获取文件对象
    path = 'tempImage'
    file_path = os.path.join(path, file.filename)
    file.save(file_path)
    img = paddle.dataset.image.load_image(file_path)
    index,acc = info.predict(img)
    res = {
        'index': index,
        'acc': acc
    }
    return jsonify(res)

info = Info() #加载模型
app.run(host='192.168.2.127', port=5003)  # 需要外网访问得自己做穿透

