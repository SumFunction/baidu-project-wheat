package com.lrr.demo.generator.upload;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class upload {



    @ResponseBody
    @RequestMapping("/upload")
    public MsgTo upload(@RequestParam("imgFile") MultipartFile imgFile, HttpServletRequest request, HttpSession session){

        MsgTo msg = new MsgTo();
        if (imgFile.isEmpty()) {
            // 设置错误状态码
            msg.setCode(500);
            msg.setMsg("上传失败，请选择文件");
            return msg;
        }


        // 拿到文件名
        String filename = imgFile.getOriginalFilename();

        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        System.out.println(fileDir.getAbsolutePath());

        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
            System.out.println(newFile.getAbsolutePath());

            // 上传图片到 -》 “绝对路径”
            imgFile.transferTo(newFile);

            msg.setMsg(newFile.getAbsolutePath());
            msg.setCode(200);
        } catch ( IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

}
