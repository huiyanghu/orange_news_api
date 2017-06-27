package com.it7890.orange.api.Servlet;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.it7890.orange.api.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "resetPassWordServlet", urlPatterns = {"/resetPassWord"})
public class resetPassWordServlet extends HttpServlet {

  private static final long serialVersionUID = 110533133254086356L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/json");
    int resultCode = Constants.CODE_SUCCESS;
    String resultMsg = "成功";
    String email = req.getParameter("email");
    AVObject avObject = new AVObject();
    List<AVObject> ls = new ArrayList<>();
    AVQuery avQuery = new AVQuery("ResetPassLogs");
    avQuery.whereEqualTo("email",email);
    avQuery.addDescendingOrder("createdAt");
    avQuery.limit(1);
    try {
      ls = avQuery.find();
      if (ls==null&&ls.size()==0){
        resultCode = Constants.CODE_SERVER_FAIL;
        resultMsg = "请重发邮件";
      }else {
        String email1 = ls.get(0).getString("email");
        String newPassWord = ls.get(0).getString("newPassWord");
        //开始重新设置密码
        AVQuery avQuery1 = new AVQuery("_User");
        avQuery1.whereEqualTo("email",email);
        List<AVObject> avObjects = avQuery1.find();
        AVObject avObject1 = new AVObject();
        avObject1.setObjectId(avObjects.get(0).getObjectId());
        avObject1.put("password",newPassWord);
        avObject1.save();
      }
    } catch (AVException e) {
      resultCode = Constants.CODE_SERVER_FAIL;
      resultMsg = "服务器错误";
      e.printStackTrace();
    }
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("code", resultCode);
    resultMap.put("msg", resultMsg);
    resp.getWriter().println(JSON.toJSONString(resultMap));
  }

  @Override
  protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
          throws ServletException, IOException {
    doGet(arg0, arg1);
  }
}
