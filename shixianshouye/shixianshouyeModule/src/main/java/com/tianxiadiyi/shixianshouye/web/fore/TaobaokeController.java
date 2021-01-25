package com.tianxiadiyi.shixianshouye.web.fore;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkActivityInfoGetRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.response.TbkActivityInfoGetResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import com.tianxiadiyi.shixianshouye.constant.Taobaoke;
import com.tianxiadiyi.shixianshouye.taobaoke.mapper.HuoDongMapper;
import com.tianxiadiyi.shixianshouye.taobaoke.pojo.TaoBaoHuoDong;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class TaobaokeController {
    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private ObjectMapper objectMapper;



    @GetMapping("/tbconfig")
    public String getTbconfig() throws JsonProcessingException {
        HuoDongMapper mapper = sqlSession.getMapper(HuoDongMapper.class);
        String resultjson=objectMapper.writeValueAsString(mapper.getTaoBaoConfig());
        //sqlSession.close();
        return resultjson;
    };

    @GetMapping("/allplateform")
    public String getAllPlateform() throws JsonProcessingException {
        HuoDongMapper mapper = sqlSession.getMapper(HuoDongMapper.class);
        String resultjson=objectMapper.writeValueAsString(Arrays.asList((mapper.getAllPlateformName()).toArray()));
        //sqlSession.close();
        return resultjson;
    };


//    @GetMapping("/activityfromdb/{terminaltype}")
//    public String getActivityFromDb(@PathVariable String terminaltype, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
//
        @GetMapping("/activityfromdb")
        public String getActivityFromDb(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String plateform = request.getParameter("plateform");
        if (plateform!=null) {
            if (plateform.trim().equals("")) {
                plateform = null;
            }
        }

//        if (request.getParameter("plateform")==null){
//            plateform=null;
//        }else {
//            plateform=request.getParameter("plateform");
//        }

        String whichpage;
        if (request.getParameter("page")==null){
            whichpage="1";
        }else {
            whichpage=request.getParameter("page");
        }

        int whichpageint = Integer.parseInt(whichpage);
        if (whichpageint < 1) {
            return "page is not right!";
        }

        String pagecount;
        if (request.getParameter("pagecount")==null){
            pagecount="20";
        }else {
            pagecount=request.getParameter("pagecount");
        }

        int pagecountint = Integer.parseInt(pagecount);
        if (pagecountint < 5) {
            return "pagecount is not right!";
        }

        int start= (whichpageint - 1) * pagecountint;
        int end= pagecountint;

        HuoDongMapper mapper = sqlSession.getMapper(HuoDongMapper.class);


        String terminaltypeex=request.getParameter("terminal");
        //terminaltype.equals("mobile")
        //if (terminaltype=="pc"){

            if (terminaltypeex!=null) {
                if (terminaltypeex.equals("pc")) {
                    terminaltypeex = "1";
                    //}else if (terminaltype=="mobile")
                } else if (terminaltypeex.equals("mobile")) {
                    terminaltypeex = "2";
                }
            }

        String searchkey=request.getParameter("searchkey");
        if((searchkey != null) && (!searchkey.trim().equals(""))){
            String searchkeyex=searchkey.trim();
            //String searchkeyex= trim(searchkey)
            //String changeres="";
            StringBuilder changeres=new StringBuilder();
            int length=searchkeyex.length();
            for (int i=0; i<length; i++){
              char item = searchkeyex.charAt(i);
                //changeres.append(String.valueOf(item));
                changeres.append(item);
                changeres.append("%");
            }
            changeres.insert(0, "%");
            searchkey=changeres.toString();
        }
        else {
            searchkey=null;
        }

        List<TaoBaoHuoDong> taoBaoHuoDongList= mapper.getAllHuoDong(terminaltypeex, plateform, searchkey, df.format(day),  start, end);
        String json = objectMapper.writeValueAsString(Arrays.asList(taoBaoHuoDongList.toArray()));
        //qlSession.close();
        return "{\"data\":" + json + "}";
    }



    @GetMapping("/getjuhuasuancommodity")
    public String getJuhuasuanCommodity() {
//        TaobaoClient client = new DefaultTaobaoClient(url_taobaoke, appkey_taobaoke, secret_taobaoke);
//        JuItemsSearchRequest req = new JuItemsSearchRequest();
//        TopItemQuery obj1 = new TopItemQuery();
//        obj1.setCurrentPage(1L);
//        obj1.setPageSize(20L);
//        obj1.setPid("-");
//        obj1.setPostage(true);
//        obj1.setStatus(2L);
//        obj1.setTaobaoCategoryId(1000L);
//        obj1.setWord("test");
//        req.setParamTopItemQuery(obj1);
//        JuItemsSearchResponse rsp = client.execute(req);
//        System.out.println(rsp.getBody());
        return "";
    }



    @GetMapping("/bkDgOptimusMaterial")
    public String getBkDgOptimusMaterial() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(Taobaoke.url_taobaoke, Taobaoke.appkey_taobaoke, Taobaoke.secret_taobaoke);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        //req.setPageSize(20L);
        req.setAdzoneId(Taobaoke.adzoneId_taobaoke);
        //req.setPageNo(1L);
        req.setMaterialId(Taobaoke.MaterialId_taobaoke_jinribaokuang);
//        req.setDeviceValue("xxx");
//        req.setDeviceEncrypt("MD5");
//        req.setDeviceType("IMEI");
//        req.setContentId(323L);
//        req.setContentSource("xxx");
//        req.setItemId(33243L);
//        req.setFavoritesId("123445");
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        //System.out.println(rsp.getBody());

        return rsp.getBody();
    }


    @GetMapping("/goodlist/{type}/{page}")//REST风格提参
    public String getgoodlist(@PathVariable Long type, @PathVariable Long page) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(Taobaoke.url_taobaoke, Taobaoke.appkey_taobaoke, Taobaoke.secret_taobaoke);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setAdzoneId(Taobaoke.adzoneId_taobaoke);
        req.setPageNo(page);
        req.setMaterialId(type);
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        //System.out.println(rsp.getBody());

        return rsp.getBody();
    }

    @GetMapping("/goodlist")//通过request提取参数
    public String getgoodlist(HttpServletRequest request, HttpServletResponse response) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(Taobaoke.url_taobaoke, Taobaoke.appkey_taobaoke, Taobaoke.secret_taobaoke);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setAdzoneId(Taobaoke.adzoneId_taobaoke);
        req.setPageNo(Long.valueOf(request.getParameter("page")));
        req.setMaterialId(Long.valueOf(request.getParameter("type")));
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        //System.out.println(rsp.getBody());

        return rsp.getBody();
    };

    String activityMaterialId_taobaoke = "20150318020000718";//"20150318020000462";

    @ResponseBody
    @GetMapping(value = {"/getOfficalActivity"})
    public String getOfficalActivity(HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) throws ApiException {
    //public String getOfficalActivity() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(Taobaoke.url_taobaoke, Taobaoke.appkey_taobaoke, Taobaoke.secret_taobaoke);
        TbkActivityInfoGetRequest req = new TbkActivityInfoGetRequest();
        req.setAdzoneId(Taobaoke.adzoneId_taobaoke);
        //req.setSubPid("mm_1_2_3");
        //req.setRelationId(123L);
        if (request.getParameter("activityid") != null){
            activityMaterialId_taobaoke = request.getParameter("activityid");
        }
        req.setActivityMaterialId(activityMaterialId_taobaoke);
        //req.setUnionId("demo");
        TbkActivityInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());

        return rsp.getBody();
    };


}
