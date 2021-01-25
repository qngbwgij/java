package com.tianxiadiyi.shixianshouye.taobaoke.mapper;

import com.tianxiadiyi.shixianshouye.taobaoke.pojo.TaoBaoConfig;
import com.tianxiadiyi.shixianshouye.taobaoke.pojo.TaoBaoHuoDong;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HuoDongMapper {
    //@Select("select image_link, activity_name, link_url from jspxcms.tabaoke_activity order by ID asc limit 10")
    @Select({"<script>",
            "SELECT image_link, activity_name, link_url, whichplateform, isofficialRecom, isofficialRecomimgurl ",
            "from tabaoke_activity WHERE (image_link, activity_name, whichplateform, ID) in ",
            "(",
            "select image_link, activity_name, whichplateform, max(ID) ",
            "from tabaoke_activity ",


            "<where>",
                    "<if test='terminaltype != null'> ",
                    " terminal_type=#{terminaltype} ",
                    "</if> ",



                    "<if test='searchkey != null'> ",
                    " activity_name like #{searchkey} ",
                    "</if> ",




            //"<choose> ",

                    "<if test='whichplateform != null'> ",
                    "and whichplateform=#{whichplateform} ",
                    //and starttime<=#{now} and endtime >=#{now} GROUP BY image_link, activity_name, whichplateform) ORDER BY ID asc LIMIT #{first}, #{second} ",
                    "</if> ",

            //"<otherwise> ",

//            "<if test='now != null'> ",
//            "and starttime<=#{now} ",
//            "</if> ",

            "<if test='now != null'> ",
            //"and starttime<=#{now}  and endtime>=#{now} ",
            "and #{now} between starttime and endtime ",
            "</if> ",

            "</where>",

            //"and starttime<=#{now} and endtime>=#{now}",

                    "GROUP BY image_link, activity_name, whichplateform",

            ") ",
                     "ORDER BY ID asc ",

                    "<if test='first != null and second != null'>",
                    "LIMIT #{first}, #{second}",
                    "</if> ",
            //"</otherwise> ",



            //"</choose> ",
            "</script>"})

//    @Select({"<script>",
//            "select * from Author",
//            "  <where>",
//            "    <if test='terminaltype != null'>terminaltype=#{terminaltype},</if>",
//            "    <if test='whichplateform != null'>whichplateform=#{whichplateform},</if>",
//            "  </where>",
//            "</script>"})
    List<TaoBaoHuoDong> getAllHuoDong(@Param("terminaltype") String terminaltype,
                                      @Param("whichplateform") String whichplateform,
                                      @Param("searchkey") String searchkey,
                                      @Param("now") String now, @Param("first") int first, @Param("second") int second);

    @Select("SELECT activitycount1, activitycount2 FROM taobaoke_config")
    TaoBaoConfig getTaoBaoConfig();

    @Select("SELECT DISTINCT whichplateform FROM tabaoke_activity")
    List<String> getAllPlateformName();
}
