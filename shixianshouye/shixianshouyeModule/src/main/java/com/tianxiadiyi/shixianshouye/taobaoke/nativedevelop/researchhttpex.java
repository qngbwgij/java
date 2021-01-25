package com.tianxiadiyi.shixianshouye.taobaoke.nativedevelop;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.newBufferedReader;

public class researchhttpex {
    //HTTPClient

    String urlAddress = "http://192.168.1.102:8080/qualityserver/login.do";
//    publicHttpClientServer(){
//
//    }

    public String doGet(String username,String password){
        String getUrl = urlAddress + "?username="+username+"&password="+password;
        HttpGet httpGet =new HttpGet(getUrl);
        HttpParams hp = httpGet.getParams();
        hp.getParameter("true");
//hp.
//httpGet.setp
        HttpClient hc =new DefaultHttpClient();
        try{
            HttpResponse ht = hc.execute(httpGet);
            if(ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity he = ht.getEntity();
                InputStream is = he.getContent();
                //BufferedReader br =newBufferedReader(new InputStreamReader(is));

                BufferedReader br =new BufferedReader((Reader) new InputStreamReader(is));
                String response = "";
                String readLine =null;
                while((readLine =br.readLine()) !=null){
//response = br.readLine();
                    response = response + readLine;
                }
                is.close();
                br.close();

//String str = EntityUtils.toString(he);
                System.out.println("========="+response);
                return response;
            }else{
                return "error";
            }
        }catch(ClientProtocolException e) {
//TODO Auto-generated catch block
            e.printStackTrace();
            return"exception";
        }catch(IOException e) {
//TODO Auto-generated catch block
            e.printStackTrace();
            return"exception";
        }
    }

    public String doPost(String username,String password){
//String getUrl = urlAddress + "?username="+username+"&password="+password;
        HttpPost httpPost =new HttpPost(urlAddress);
        List params =new ArrayList();
        NameValuePair pair1 =new BasicNameValuePair("username", username);
        NameValuePair pair2 =new BasicNameValuePair("password", password);
        params.add(pair1);
        params.add(pair2);

        HttpEntity he;
        try{
            he =new UrlEncodedFormEntity(params, "gbk");
            httpPost.setEntity(he);

        }catch(UnsupportedEncodingException e1) {
//TODO Auto-generated catch block
            e1.printStackTrace();
        }

        HttpClient hc =new DefaultHttpClient();
        try{
            HttpResponse ht = hc.execute(httpPost);
//连接成功
            if(ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity het = ht.getEntity();
                InputStream is = het.getContent();
                //BufferedReader br =newBufferedReader(new InputStreamReader(is));

                BufferedReader br =newBufferedReader((Path) new InputStreamReader(is));
                String response = "";
                String readLine =null;
                while((readLine =br.readLine()) !=null){
//response = br.readLine();
                    response = response + readLine;
                }
                is.close();
                br.close();

//String str = EntityUtils.toString(he);
                System.out.println("=========&&"+response);
                return response;
            }else{
                return"error";
            }
        }catch(ClientProtocolException e) {
//TODO Auto-generated catch block
            e.printStackTrace();
            return"exception";
        }catch(IOException e) {
//TODO Auto-generated catch block
            e.printStackTrace();
            return"exception";
        }
    }
    //servlet端json转化：

//
//    try{
//
//    resp.setContentType("text/json");
//    resp.setCharacterEncoding("UTF-8");
//    toDo =new ToDo();
//    List<UserBean> list =new ArrayList<UserBean>();
//    list = toDo.queryUsers(mySession);
//    String body;
//
//    //设定JSON
//    JSONArray array =new JSONArray();
//    for(UserBean bean : list)
//    {
//        JSONObject obj =newJSONObject();
//        try
//        {
//            obj.put("username", bean.getUserName());
//            obj.put("password", bean.getPassWord());
//        }catch(Exception e){}
//        array.add(obj);
//    }
//
//    pw.write(array.toString());
//    System.out.println(array.toString());
//
//    //android端接收：
//
//    String urlAddress = "http://192.168.1.102:8080/qualityserver/result.do";
//    String body =
//            getContent(urlAddress);
//    JSONArray array =newJSONArray(body);
//
//    for(inti=0;i<array.length();i++)
//    {
//        obj = array.getJSONObject(i);
//        sb.append("用户名:").append(obj.getString("username")).append("\t");
//        sb.append("密码:").append(obj.getString("password")).append("\n");
//
//        HashMap<String, Object> map =newHashMap<String, Object>();
//        try{
//            userName = obj.getString("username");
//            passWord = obj.getString("password");
//        }catch(JSONException e) {
//            e.printStackTrace();
//        }
//        map.put("username", userName);
//        map.put("password", passWord);
//        listItem.add(map);
//
//    }
//
//   }catch(Exception e) {
//        //TODO Auto-generated catch block
//        e.printStackTrace();
//        }
//
//        if(sb!=null)
//        {
//        showResult.setText("用户名和密码信息：");
//        showResult.setTextSize(20);
//        }else
//        extracted();
//
//        //设置adapter
//        SimpleAdapter simple =newSimpleAdapter(this,listItem,
//        android.R.layout.simple_list_item_2,
//        newString[]{"username","password"},
//        newint[]{android.R.id.text1,android.R.id.text2});
//
//        listResult.setAdapter(simple);
//
//        listResult.setOnItemClickListener(newOnItemClickListener() {
//        @Override
//        publicvoidonItemClick(AdapterView<?> parent, View view,
//        intposition,longid) {
//        intpositionId = (int) (id+1);
//        Toast.makeText(MainActivity.this, "ID："+positionId, Toast.LENGTH_LONG).show();
//
//        }
//        });
//
//        //}
//        privatevoidextracted() {
//        showResult.setText("没有有效的数据！");
//        }
//        //和服务器连接
//        private String getContent(String url)throws Exception{
//        StringBuilder sb =newStringBuilder();
//        HttpClient client =newDefaultHttpClient();
//        HttpParams httpParams =client.getParams();
//
//        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
//        HttpConnectionParams.setSoTimeout(httpParams, 5000);
//        HttpResponse response = client.execute(newHttpGet(url));
//        HttpEntity entity =response.getEntity();
//
//        if(entity !=null){
//        BufferedReader reader =newBufferedReader(newInputStreamReader
//        (entity.getContent(),"UTF-8"),8192);
//        String line =null;
//        while((line= reader.readLine())!=null){
//        sb.append(line +"\n");
//        }
//        reader.close();
//        }
//        return sb.toString();
//        }
//

}
