package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.modular.sbdsys.entity.Suggestion;
import cn.stylefeng.guns.modular.sbdsys.mapper.MyFileMapper;
import cn.stylefeng.guns.modular.sbdsys.mapper.SuggestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SuggestService {

    @Resource
    SuggestMapper suggestMapper;

    // 保存建议
    public int insertSuggestion(Suggestion suggestion){
       return suggestMapper.insertSuggestion(suggestion);
    }


    // 修改建议
    public int updateSuggestion(Suggestion suggestion){
        return suggestMapper.insertSuggestion(suggestion);
    }


    // 查询列表
    public List<Suggestion> selectSuggestionList(Suggestion suggestion) {
        return suggestMapper.selectSuggestionList(suggestion);
    }

    // 查询总数
    public Suggestion selectSuggestionListCount(Suggestion suggestion) {
        return suggestMapper.selectSuggestionListCount(suggestion);
    }

    // 查询详细
    public Suggestion selectSuggestionDetail(Suggestion suggestion) {
        return suggestMapper.selectSuggestionDetail(suggestion);
    }
}
