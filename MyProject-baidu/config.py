import yaml
config = {}
path = r"./config.yaml"
with open(path,'r',encoding='utf-8') as f:
    config = f.read()
    config = yaml.safe_load(config)