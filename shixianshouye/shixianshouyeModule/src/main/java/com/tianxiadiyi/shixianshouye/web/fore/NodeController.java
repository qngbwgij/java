package com.tianxiadiyi.shixianshouye.web.fore;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkActivityInfoGetRequest;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.response.TbkActivityInfoGetResponse;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.tianxiadiyi.shixianshouye.constant.Taobaoke;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.jspxcms.common.web.Servlets;
//import com.jspxcms.common.web.Validations;
import com.tianxiadiyi.shixianshouye.constant.Constants;
//import com.jspxcms.core.domain.MemberGroup;
import com.tianxiadiyi.shixianshouye.domain.Node;
//import com.jspxcms.core.domain.Org;
import com.tianxiadiyi.shixianshouye.domain.Site;
//import com.jspxcms.core.domain.User;
//import com.jspxcms.core.service.NodeBufferService;
import com.tianxiadiyi.shixianshouye.service.NodeQueryService;
import com.tianxiadiyi.shixianshouye.support.Context;
//import com.jspxcms.core.support.ForeContext;
//import com.jspxcms.core.support.Response;
import com.tianxiadiyi.shixianshouye.support.SiteResolver;

/**
 * NodeController
 *
 * @author liufang
 */
@Controller
public class NodeController {
    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) throws Exception {
        //return index(null, request, response, modelMap);
        return "/vue/indexwujain.html";
        //return "index.html";
        //throw new Exception("错误页面来自邬剑！");

    }


    @ResponseBody
    @GetMapping(value = {"/gettaobaodata"})
    //public String index(HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) throws ApiException {
    public String index() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(Taobaoke.url_taobaoke, Taobaoke.appkey_taobaoke, Taobaoke.secret_taobaoke);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
//        req.setStartDsr(10L);
//        req.setPageSize(20L);
//        req.setPageNo(1L);
//        req.setPlatform(1L);
//        req.setEndTkRate(1234L);
//        req.setStartTkRate(1234L);
//        req.setEndPrice(10L);
//        req.setStartPrice(10L);
//        req.setIsOverseas(false);
//        req.setIsTmall(false);
//        req.setSort("tk_rate_des");
        req.setItemloc("杭州");
//        req.setCat("16,18");
        req.setQ("女装");
//        req.setMaterialId(2836L);
//        req.setHasCoupon(false);
//        req.setIp("13.2.33.4");
//        req.setAdzoneId(12345678L);


        req.setAdzoneId(Taobaoke.adzoneId_taobaoke);

//        req.setNeedFreeShipment(true);
//        req.setNeedPrepay(true);
//        req.setIncludePayRate30(true);
//        req.setIncludeGoodRate(true);
//        req.setIncludeRfdRate(true);
//        req.setNpxLevel(2L);
//        req.setEndKaTkRate(1234L);
//        req.setStartKaTkRate(1234L);
//        req.setDeviceEncrypt("MD5");
//        req.setDeviceValue("xxx");
//        req.setDeviceType("IMEI");
//        req.setLockRateEndTime(1567440000000L);
//        req.setLockRateStartTime(1567440000000L);
//        req.setLongitude("121.473701");
//        req.setLatitude("31.230370");
//        req.setCityCode("310000");
//        req.setSellerIds("1,2,3,4");
//        req.setSpecialId("2323");
//        req.setRelationId("3243");


        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        return  rsp.getBody();

    }



    @GetMapping(value = Constants.SITE_PREFIX_PATH)
    public String index(@PathVariable String siteNumber, HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) {
        siteResolver.resolveSite(siteNumber);
        Site site = Context.getCurrentSite();
//        Response resp = new Response(request, response, modelMap);
//        List<String> messages = resp.getMessages();
        Node node = query.findRoot(site.getId());
//        if (!Validations.exist(node, messages, "Node", "root")) {
//            return resp.badRequest();
//        }
//        Collection<MemberGroup> groups = Context.getCurrentGroups(request);
//        Collection<Org> orgs = Context.getCurrentOrgs(request);
//        if (!node.isViewPerm(groups, orgs)) {
//            User user = Context.getCurrentUser();
//            if (user != null) {
//                return resp.forbidden();
//            } else {
//                return resp.unauthorized();
//            }
//        }
//        modelMap.addAttribute("node", node);
//        modelMap.addAttribute("text", node.getText());
//
//        ForeContext.setData(modelMap.asMap(), request);
        return node.getTemplate();//   /1/default/index.html
//        String template = Servlets.getParam(request, "template");
//        if (StringUtils.isNotBlank(template)) {
//            return template;
//        } else {
//            return node.getTemplate();
//        }
    }

//    @GetMapping("/node/{id:[0-9]+}")
//    public String node(@PathVariable Integer id,
//                       HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) {
//        return node(null, id, 1, request, response, modelMap);
//    }
//
//    @GetMapping(Constants.SITE_PREFIX_PATH + "/node/{id:[0-9]+}")
//    public String node(@PathVariable String siteNumber, @PathVariable Integer id,
//                       HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) {
//        return node(siteNumber, id, 1, request, response, modelMap);
//    }
//
//    @GetMapping("/node/{id:[0-9]+}_{page:[0-9]+}")
//    public String node(@PathVariable Integer id, @PathVariable Integer page,
//                       HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) {
//        return node(null, id, page, request, response, modelMap);
//    }
//
//    @GetMapping(Constants.SITE_PREFIX_PATH + "/node/{id:[0-9]+}_{page:[0-9]+}")
//    public String node(@PathVariable String siteNumber, @PathVariable Integer id, @PathVariable Integer page,
//                       HttpServletRequest request, HttpServletResponse response, org.springframework.ui.Model modelMap) {
//        Node node = query.get(id);
//        siteResolver.resolveSite(siteNumber, node);
//        Site site = Context.getCurrentSite();
//        Response resp = new Response(request, response, modelMap);
//        List<String> messages = resp.getMessages();
//        if (!Validations.exist(node, messages, "Node", id)) {
//            return resp.badRequest();
//        }
//        if (!node.getSite().getId().equals(site.getId())) {
//            site = node.getSite();
//            Context.setCurrentSite(site);
//        }
//        Collection<MemberGroup> groups = Context.getCurrentGroups(request);
//        Collection<Org> orgs = Context.getCurrentOrgs(request);
//        if (!node.isViewPerm(groups, orgs)) {
//            User user = Context.getCurrentUser();
//            if (user != null) {
//                return resp.forbidden();
//            } else {
//                return resp.unauthorized();
//            }
//        }
//        String linkUrl = node.getLinkUrl();
//        if (StringUtils.isNotBlank(linkUrl)) {
//            return "redirect:" + linkUrl;
//        }
//        modelMap.addAttribute("node", node);
//        modelMap.addAttribute("text", node.getText());
//
//        Map<String, Object> data = modelMap.asMap();
//        ForeContext.setData(data, request);
//        ForeContext.setPage(data, page, node);
//        return node.getTemplate();
//        String template = Servlets.getParam(request, "template");
//        if (StringUtils.isNotBlank(template)) {
//            return template;
//        } else {
//            return node.getTemplate();
//        }
//    }


//    @ResponseBody
//    @GetMapping(value = {"/node_views/{id:[0-9]+}", Constants.SITE_PREFIX_PATH + "/node_views/{id:[0-9]+}"})
//    public String views(@PathVariable Integer id) {
//        return Integer.toString(bufferService.updateViews(id));
//    }

    @Autowired
    private SiteResolver siteResolver;
//    @Autowired
//    private NodeBufferService bufferService;
    @Autowired
    private NodeQueryService query;
}
