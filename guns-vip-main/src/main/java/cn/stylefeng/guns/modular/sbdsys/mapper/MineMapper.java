package cn.stylefeng.guns.modular.sbdsys.mapper;

import cn.stylefeng.guns.modular.sbdsys.entity.*;
import cn.stylefeng.guns.modular.sbdsys.model.params.AccountParam;
import cn.stylefeng.guns.modular.sbdsys.model.result.AccountResult;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface MineMapper extends BaseMapper<Account> {

   List<Census> selectUserContactList(@Param("census") Census census);

   // 用户个人中心 基本信息
   Census selectUserRoleInfo(@Param("census") Census census);
   // 用户个人中心 所在支部信息
   List<Census> selectUserBranchInfo(@Param("census") Census census);
   // 用户 - 党员基本信息
   MineAccount selectBaseUserInfo(MineAccount mineAccount);

   // 党员积分统计 按 月 年 历史统计 活动
   Census selectActivityScore(@Param("census") Census census);
   // 党员积分统计 按 月 年 历史统计 心愿
   Census selectWishScore(@Param("census") Census census);

    // 党员 参加 活动  图表 数据
    List<Census> selectUserActivityCensusChartByMonth(@Param("census") Census census);

    // 党员 参加 心愿  图表 数据
    List<Census> selectUserWishCensusChartByMonth(@Param("census") Census census);


   // 社区 - 基本信息
   MineCommunity selectBaseCommunityInfo(@Param("census") MineCommunity mineCommunity);
   // 社区 积分统计 按 月 年 历史统计 活动
   Census selectCommunityActivityScore(@Param("census") Census census);
   // 社区 积分统计 按 月 年 历史统计 心愿
   Census selectCommunityWishScore(@Param("census") Census census);

   // 社区 参加人数 按 月 年 历史统计 活动
   Census selectCommunityActivityCensusPerson(@Param("census") Census census);
   // 社区 参加人数 按 月 年 历史统计 心愿
   Census selectCommunityWishCensusPerson(@Param("census") Census census);

   // 社区 活动 按 月 图表 数据
   List<Census> selectCommunityActivityCensusChartByMonth(@Param("census") Census census);
   // 社区 心愿 按 月 图表 数据
   List<Census> selectCommunityWishCensusChartByMonth(@Param("census") Census census);




   //活动 心愿  分类统计
   List<Census> selectActivityType(@Param("census") Census census);
   List<Census> selectWishType(@Param("census") Census census);


//   党支部 基本信息
   MineBranch selectBaseBranchInfo(@Param("census") MineBranch mineBranch);

   Census selectBranchScore(@Param("census") Census census);


   // 党支部 人员列表
   List<Census> selectBranchMemberList(@Param("census") Census census);

   // 党支部 人员排行
   List<Census> selectBranchMemberRank(@Param("census") Census census);
   // 党支部 排行
   List<Census> selectBranchRank(@Param("census") Census census);


   // 党支部 活动 按 月 图表 数据
   List<Census> selectBranchActivityCensusChartByMonth(@Param("census") Census census);
   // 党支部  心愿 按 月 图表 数据
   List<Census> selectBranchWishCensusChartByMonth(@Param("census") Census census);



   // 党委信息
   MineCommittee selectBaseCommitteeInfo(@Param("census") MineCommittee mineCommittee);

   List<Census> selectCommitteeBranchRank(@Param("census") Census census);





   // 全部数据统计
   Census selectActivityAllCensusCommunity(@Param("census") Census census);

   Census selectActivityAllCensusPerson(@Param("census") Census census);


   // 全部数据统计
   Census selectWishAllCensusCommunity(@Param("census") Census census);

   Census selectWishAllCensusPerson(@Param("census") Census census);



   List<Census> selectBranchList(@Param("census") Census census);

   List<Census> selectMemberRank(@Param("census") Census census);

   List<Census> selectBranchRankSingle(@Param("census") Census census);

   List<Census> selectMemberRankSingle(@Param("census") Census census);

   List<Census> selectRelationBranch();

   List<Census> selectRelationCommunity();

   List<Census> selectRelationBranchAndCommunity();


   List<Census> selectRelationBranchAndCommunityByBranchId(@Param("census") Census census);

}
