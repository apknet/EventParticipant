package com.ep.eventparticipant.others;





import com.ep.eventparticipant.Item.All_item;
import com.ep.eventparticipant.Item.Exchangeitem;
import com.ep.eventparticipant.fragment.furnitureFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.ep.eventparticipant.fragment.FragmentSwap.exchangeitemList;



/**
 * 接口请求工具类
 *
 * @author As_
 * @since 2018/07/24
 * @email apknet@163.com
 * @github https://github.com/apknet
 *
 */

public class AsHttpUtils {
    private static   OkHttpClient client = new OkHttpClient();
    private static String cookie;
    public static List<All_item> all_items=new ArrayList<>();

//    public static int login(String user, String pass){
//        String url = String.format("http://120.79.137.167:8080/firstProject/user/login.do?userName=%s&password=%s", user, pass);
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        String result = "";
//        try {
//            Response response = client.newCall(request).execute();
//            //获得返回的主体
//            result = response.body().string();
//            //获得返回头部信息中的Cookie信息并处理
//            String setCookie = response.header("Set-Cookie");
//            cookie = setCookie.substring(0, setCookie.indexOf(";")).trim();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            JSONObject jb = new JSONObject(result).getJSONObject("data");
//            curUser.setId(jb.getInt("id"));
//            curUser.setName(jb.getString("name"));
//            curUser.setUsername(jb.getString("username"));
//            curUser.setSignature(jb.getString("signature"));
//            curUser.setImageurl(jb.getString("imageurl"));
//            curUser.setPhone(jb.getString("phone"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            return new JSONObject(result).getInt("status");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

    public static int register(String name, String userName, String pass){
        String url = String.format("http://120.79.137.167:8080/firstProject/user/register.do?userName=%s&password=%s&name=%s",
                userName, pass, name);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            int code = new JSONObject(response.body().toString()).getInt("status");
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    //图片上传
    public static String upImage(File file){
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("upload_activity_picture_file", "data.png",
                        RequestBody.create(MediaType.parse("image/jpg"), file))
                .build();
        Request request = new Request.Builder()
                .url("http://120.79.137.167:8080/firstProject/activity/uploadActivityPicture.do")
                .header("Cookie", cookie)
                .post(requestBody)
                .build();
        String result = "";
        try {
            Response response = client.newCall(request).execute();
            result = response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = "";
        try {
            imgUrl = new JSONObject(result).getJSONObject("data").getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imgUrl;
    }

    //    发布活动
    public static int createActivity(int id, String name, String time, String address, String note, String imgUrl){
        String url = String.format(
                "http://120.79.137.167:8080/firstProject/activity/release.do?id=%d&name=%s&time=%s&address=%s&introduction=%s&imageurl=%s",
                id, name, time, address, note, imgUrl);

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            int code = new JSONObject(response.body().string()).getInt("status");
            // 0成功， 1失败
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    //    修改活动
    public static int updateActivity(int id, String name, String time, String address, String note, String imgUrl){
        String url = String.format(
                "http://120.79.137.167:8080/firstProject/activity/release.do?id=%d&name=%s&time=%s&address=%s&introduction=%s&imageurl=%s",
                id, name, time, address, note, imgUrl);

        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            int code = new JSONObject(response.body().string()).getInt("status");
            // 0成功， 1失败
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    //    报名活动
    public static int joinActivity(int id){
        String url = String.format("http://120.79.137.167:8080/firstProject/activity_member/sign_up.do?activityId=%d", id);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            int code = new JSONObject(response.body().string()).getInt("status");
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    //    取消报名
    public static int exitActivity(int id){
        String url = String.format("http://120.79.137.167:8080/firstProject/activity_member/cancel_sign_up.do?activityId=%d", id);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            int code = new JSONObject(response.body().string()).getInt("status");
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    //    活动列表
//    public static int allActivity(){
//        Request request = new Request.Builder()
//                .url("http://120.79.137.167:8080/firstProject/activity/List.do")
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            JSONObject jsonObject = new JSONObject(response.body().string());
//            int code = jsonObject.getInt("status");
//            JSONArray ja = jsonObject.getJSONObject("data").getJSONArray("list");
//            if (code == 0) {
//                eventBeanList.clear();
//                for (int i = 0; i < ja.length(); i++) {
//                    JSONObject jb = ja.getJSONObject(i);
//                    EventBean eventBean = new EventBean();
//                    eventBean.setId(jb.getInt("activityId"));
//                    eventBean.setName(jb.getString("activityName"));
//                    eventBean.setStartTime(jb.getString("activityTime").substring(0, jb.getString("activityTime").indexOf("-")).trim());
//                    eventBean.setEndTime(jb.getString("activityTime").substring(jb.getString("activityTime").indexOf("-") + 1));
//                    eventBean.setWhere(jb.getString("address"));
//                    eventBean.setImgUri("activityImageurl");
//                    eventBean.setNote(jb.getString("introduction"));
//
////                eventBean.setOrganizerId(0);
//                    eventBean.setOrganizerName(jb.getString("createrName"));
//                    eventBean.setOrganizerHeader(jb.getString("createrImageurl"));
//                    eventBean.setOrganizerNote(jb.getString("createrSignature"));
//                    eventBean.setOrganizerTel(jb.getString("createrPhone"));
//
//                    eventBean.setPersonCount(jb.getInt("peopleNumber"));
//                    eventBeanList.add(eventBean);
//                }
//            }
//            return code;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 1;
//    }


    public static int ExchangeList(){
        Request request=new Request.Builder().url("http://120.79.137.167:8080/firstProject/exchange/list.do").build();
        try {
            Response response=client.newCall(request).execute();
            //JSONObject jsonObject=new JSONObject(response.body().string());
            JSONArray jsonArray=new JSONArray(response.body().string());
            int code=jsonArray.getJSONObject(0).getInt("status");
            JSONArray js=jsonArray.getJSONArray(1);
            JSONArray ja=js.getJSONArray(8);
            //int code=jsonObject.getInt("status");
            //JSONArray ja = jsonObject.getJSONObject("data").getJSONArray("list");
            if(code==0){
                exchangeitemList.clear();
                for(int i=0;i<ja.length();i++){
                    JSONObject object=ja.getJSONObject(i);
                    String imageUrl=null;
                    imageUrl=  object.getString("imageUrl");

                    String name=object.getString("name");
                    String time=object.getString("time");
                    // String address="武汉科技大学";
                    String address=object.getString("address");
                     String phone=object.getString("phone");
                    String expect=object.getString("expect");
                     int price=100;
                    //int price=object.getInt("price");
                    Random random=new Random();
                    int userID=random.nextInt();
                    Exchangeitem exchangeitem=new Exchangeitem(time,name,imageUrl,address,price,phone,String.valueOf(userID),expect);
                    exchangeitemList.add(exchangeitem);
                }
                return code;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

       // 搜索交换
    public static int searchExchange(String args, boolean isInt){
        String url_int = String.format("http://120.79.137.167/exchange/selectLike.do?name=%s", args);
        String url_str = String.format("http://120.79.137.167/exchange/selectLike.do?name=%s", args);

        Request.Builder builder = new Request.Builder();
        if (isInt) {
            builder.url(url_int);
        }else{
            builder.url(url_str);
        }
        Request request = builder.build();
        try {
            Response response = client.newCall(request).execute();
           // JSONObject jsonObject = new JSONObject(response.body().string());
            JSONArray jsonArray=new JSONArray(response.body().string());
            int code = jsonArray.getJSONObject(0).getInt("status");

            //JSONArray ja = jsonObject.getJSONObject("data").getJSONArray("list");
            JSONArray ja=jsonArray.getJSONArray(1);
            JSONArray jb=ja.getJSONArray(8);
            all_items.clear();
            if(jb==null) return 1;
            for (int i = 0; i< jb.length(); i++){
                JSONObject jc = ja.getJSONObject(i);
                All_item all_item=new All_item();
                all_item.setAddress(jc.getString("address"));
                all_item.setExpect(jc.getString("expect"));
                all_item.setImageurl(jc.getString("imageurl"));
                all_item.setName(jc.getString("name"));
                all_item.setPrice(jc.getInt("price"));
                all_item.setTime(jc.getString("time"));
                all_items.add(all_item);

            }
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
    public static int createExchange(String name, String time, String place, String expect, String price,String url1) {
        String url = String.format("http://120.79.137.167:8080/firstProject/exchange/upload_exchange.do?name=%s&time=%s&address=%s&expect=%s&price=%s&imageurl=%s", name,time,place,expect,price,url1);

         Request request = new Request.Builder().url(url).build();


                try {
                    Response response = client.newCall(request).execute();
                   int code = new JSONObject(response.body().string()).getInt("status");
                    return code;
                } catch (Exception e){
                    e.printStackTrace();
                }
        return 1;

    }


}
