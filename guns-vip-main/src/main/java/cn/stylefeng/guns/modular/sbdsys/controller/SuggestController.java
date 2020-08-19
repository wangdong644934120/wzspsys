package cn.stylefeng.guns.modular.sbdsys.controller;

import cn.stylefeng.guns.base.shiro.ShiroUser;
import cn.stylefeng.guns.modular.sbdsys.entity.MyFile;
import cn.stylefeng.guns.modular.sbdsys.entity.Suggestion;
import cn.stylefeng.guns.modular.sbdsys.service.MyFileService;
import cn.stylefeng.guns.modular.sbdsys.service.SuggestService;
import cn.stylefeng.guns.sys.core.shiro.ShiroKit;
import cn.stylefeng.guns.util.DateUtils;
import cn.stylefeng.guns.util.PageUtils;
import cn.stylefeng.guns.util.TextUtils;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 建议反馈 控制器
 *
 * @author Admin
 * @Date 2019-09-06 10:19:01
 */
@Controller
@RequestMapping("/suggest")
public class SuggestController {

    private String PREFIX = "/suggestion";


    @Autowired
    SuggestService suggestService;


    @Autowired
    MyFileService myFileService;


    @RequestMapping("/suggestion_add")
    public String suggestionAdd(Model model){
        long userId = ShiroKit.getUser().getId();
        if (userId != 0){
            String tempId = TextUtils.getUserTempToken(userId);
            model.addAttribute("tempId", tempId);
            return PREFIX + "/suggestion_add.html";
        }


        return PREFIX + "/.html";
    }
    @RequestMapping("/suggestion_list")
    public String mySuggestion(){
        return PREFIX + "/suggestion_list.html";
    }


    @RequestMapping("/suggestion_detail")
    public String suggestionDetail(@RequestParam(value = "suggestionId", required = true) int suggestionId,
                                   Model model){
        Suggestion paramSuggestion = new Suggestion();
        paramSuggestion.setId(suggestionId);;

        Suggestion suggestionDetail = suggestService.selectSuggestionDetail(paramSuggestion);

        if (null != suggestionDetail){

            model.addAttribute("info", suggestionDetail);
            return PREFIX + "/suggestion_detail.html";
        }

        model.addAttribute("message", "数据不存在");
        return PREFIX + "/suggestion_list.html";
    }


    @ResponseBody
    @PostMapping("/saveSuggestion")
    public ResponseData saveSuggestion(@RequestParam(value = "content", required = true) String content ,
                                       @RequestParam(value = "tempId", required = true) String tempId){
        long userId = ShiroKit.getUser().getId();

        Suggestion suggestion = new Suggestion();

        String currentDate = DateUtils.getCurrentDate();

        suggestion.setContent(content);
        suggestion.setCreateDate(currentDate);
        suggestion.setCreateUser(userId);
        suggestion.setUpdateDate(currentDate);
        suggestion.setUpdateUser(userId);
        suggestion.setRemark("");
        suggestion.setIsShow(0);
        suggestion.setIsDel(0);
        suggestion.setStatus(1);


        int i =  suggestService.insertSuggestion(suggestion);


        int suggestId = suggestion.getId();

        if (suggestId > 0){
            MyFile myFile = new MyFile();
            myFile.setObjId(String.valueOf(suggestId));
            myFile.setTempId(tempId);
            myFileService.updateFileWith(myFile);
        }


        return ResponseData.success();

    }
    @ResponseBody
    @GetMapping("/getSuggestionDetail")
    public ResponseData getSuggestionDetail(@RequestParam(value = "suggestionId", required = true) String suggestionId){
        if (TextUtils.isEmpty(suggestionId)){
            return ResponseData.error("参数错误");
        }
        int id = Integer.valueOf(suggestionId);
        Suggestion paramSuggestion = new Suggestion();
        paramSuggestion.setId(id);;

        Suggestion suggestionDetail = suggestService.selectSuggestionDetail(paramSuggestion);

        HashMap rsMap = new HashMap();
        rsMap.put("info", suggestionDetail);

        return ResponseData.success(rsMap);

    }

    @ResponseBody
    @GetMapping("/getMySuggestionList")
    public ResponseData getMySuggestionList(@RequestParam(value = "words", required = false) String words,
                                          @RequestParam(value = "pageIndex", required = false) String pageIndex,
                                          @RequestParam(value = "pageSize", required = false) String pageSize
                                          ){
        ShiroUser user = ShiroKit.getUser();
        long userId = user.getId();

        int __pageIndex = TextUtils.getIntegerValueOfString(pageIndex);
        int __pageSize = TextUtils.getIntegerValueOfString(pageSize);

        __pageIndex = __pageIndex == 0 ? 1 : __pageIndex;
        __pageSize = __pageSize == 0 ? 12 : __pageSize;

        Suggestion paramSuggestion = new Suggestion();

        paramSuggestion.setCreateUser(userId);
        Suggestion countSuggestion = suggestService.selectSuggestionListCount(paramSuggestion);

        int count = countSuggestion.getCount();

        PageUtils pageUtils = new PageUtils(count, __pageSize);

        String limit = pageUtils.getLimit(__pageIndex);
        int hasMore = pageUtils.hasMore(__pageIndex);
        int pagination = pageUtils.getPagination();

        paramSuggestion.setLimit(limit);
        paramSuggestion.setOrderby(" s.create_date desc");

        List<Suggestion> list = suggestService.selectSuggestionList(paramSuggestion);




        HashMap rsMap = new HashMap();

        rsMap.put("count", count);
        rsMap.put("pagination", pagination);
        rsMap.put("hasMore", hasMore);
        rsMap.put("list", list);

        return ResponseData.success(rsMap);
    }

    @ResponseBody
    @GetMapping("/getSuggestionList")
    public ResponseData getSuggestionList(@RequestParam(value = "words", required = false) String words,
                                          @RequestParam(value = "pageIndex", required = false) String pageIndex,
                                          @RequestParam(value = "pageSize", required = false) String pageSize
    ){
        ShiroUser user = ShiroKit.getUser();

        int __pageIndex = TextUtils.getIntegerValueOfString(pageIndex);
        int __pageSize = TextUtils.getIntegerValueOfString(pageSize);

        __pageIndex = __pageIndex == 0 ? 1 : __pageIndex;
        __pageSize = __pageSize == 0 ? 12 : __pageSize;

        Suggestion paramSuggestion = new Suggestion();

        Suggestion countSuggestion = suggestService.selectSuggestionListCount(paramSuggestion);

        int count = countSuggestion.getCount();

        PageUtils pageUtils = new PageUtils(count, __pageSize);

        String limit = pageUtils.getLimit(__pageIndex);
        int hasMore = pageUtils.hasMore(__pageIndex);
        int pagination = pageUtils.getPagination();

        paramSuggestion.setLimit(limit);

        paramSuggestion.setOrderby(" s.create_date desc");

        List<Suggestion> list = suggestService.selectSuggestionList(paramSuggestion);




        HashMap rsMap = new HashMap();

        rsMap.put("count", count);
        rsMap.put("pagination", pagination);
        rsMap.put("hasMore", hasMore);
        rsMap.put("list", list);

        return ResponseData.success(rsMap);
    }
}
