package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.MyFile;
import cn.stylefeng.guns.modular.sbdsys.entity.Suggestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2019-09-06
 */
public interface SuggestMapper {

   int insertSuggestion(@Param("suggestion")Suggestion suggestion);

   int deleteSuggestion(@Param("suggestion")Suggestion suggestion);

   int updateSuggestion(@Param("suggestion")Suggestion suggestion);

   int setSuggestShow(@Param("suggestion")Suggestion suggestion);


   List<Suggestion> selectSuggestionList(@Param("suggestion")Suggestion suggestion);

   Suggestion selectSuggestionListCount(@Param("suggestion")Suggestion suggestion);

   Suggestion selectSuggestionDetail(@Param("suggestion")Suggestion suggestion);
}
