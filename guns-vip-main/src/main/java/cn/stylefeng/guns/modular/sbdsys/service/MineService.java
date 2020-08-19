package cn.stylefeng.guns.modular.sbdsys.service;

import cn.stylefeng.guns.modular.sbdsys.entity.*;
import cn.stylefeng.guns.modular.sbdsys.mapper.AccountMapper;
import cn.stylefeng.guns.modular.sbdsys.mapper.MineMapper;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.mapper.FileInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MineService  {

    @Resource
    MineMapper baseMapper;

    @Resource
    FileInfoMapper fileInfoMapper;

    public FileInfo getAvatarFileById(String avatarId){
       return  fileInfoMapper.selectById(avatarId);
    }

    public List<Census> selectUserContactList(Census census){
        return baseMapper.selectUserContactList(census);
    }

    public Census selectUserRoleInfo(Census census){
        return baseMapper.selectUserRoleInfo(census);
    }

    public List<Census> selectUserBranchInfo(Census census){
        return baseMapper.selectUserBranchInfo(census);
    }
    /**
     *  用户信息
     *  用户活动积分
     *  用户心愿积分
     *
     *  用户图表  积分图， 心愿图
     * @param mineAccount
     * @return
     */
    public MineAccount selectUserInfo(MineAccount mineAccount) {
        return this.baseMapper.selectBaseUserInfo(mineAccount);
    }

    public Census selectUserActivityScore(Census census){
        return this.baseMapper.selectActivityScore(census);
    }

    public Census selectUserWishScore(Census census){
        return this.baseMapper.selectWishScore(census);
    }


    public List<Census> selectUserActivityCensusChartByMonth(Census census){
        return this.baseMapper.selectUserActivityCensusChartByMonth(census);
    }

    public List<Census> selectUserWishCensusChartByMonth(Census census){
        return this.baseMapper.selectUserWishCensusChartByMonth(census);
    }


    /**
     * 社区信息
     * @param mineCommunity
     * @return
     */

    public MineCommunity selectBaseCommunityInfo(MineCommunity mineCommunity) {
        return this.baseMapper.selectBaseCommunityInfo(mineCommunity);
    }


    /**
     * 社区信息 数据统计
     * @return
     */


    public Census selectCommunityActivityScore(Census census){
        return this.baseMapper.selectCommunityActivityScore(census);
    }

    public Census selectCommunityWishScore(Census census){
        return this.baseMapper.selectCommunityWishScore(census);
    }


    public Census selectCommunityActivityCensusPerson(Census census){
        return this.baseMapper.selectCommunityActivityCensusPerson(census);
    }

    public Census selectCommunityWishCensusPerson(Census census){
        return this.baseMapper.selectCommunityWishCensusPerson(census);
    }

    /**
     * 社区信息 图表数据
     * @return
     */
    public List<Census> selectCommunityActivityCensusChartByMonth(Census census){
        return this.baseMapper.selectCommunityActivityCensusChartByMonth(census);
    }
    public List<Census> selectCommunityWishCensusChartByMonth(Census census){
        return this.baseMapper.selectCommunityWishCensusChartByMonth(census);
    }



    /**
     *  支部信息
     *  支部排行 人员排行 所有排行
     *  支部积分统计
     *  支部图表信息
     * @param mineBranch
     * @return
     */

    public MineBranch selectBranchInfo(MineBranch mineBranch) {
        return this.baseMapper.selectBaseBranchInfo(mineBranch);
    }

    public List<Census> selectBranchMemberList(Census census){
        return this.baseMapper.selectBranchMemberList(census);
    }

    public List<Census> selectBranchMemberRank(Census census){
        return this.baseMapper.selectBranchMemberRank(census);
    }

    public  List<Census> selectBranchRank(Census census){
        return this.baseMapper.selectBranchRank(census);
    }

    public  List<Census> selectBranchRankSingle(Census census){
        return this.baseMapper.selectBranchRankSingle(census);
    }

    public Census selectBranchScore(Census census){
        return this.baseMapper.selectBranchScore(census);
    }

    public List<Census> selectBranchActivityCensusChartByMonth(Census census){
        return this.baseMapper.selectBranchActivityCensusChartByMonth(census);
    }

    public  List<Census> selectBranchWishCensusChartByMonth(Census census){
        return this.baseMapper.selectBranchWishCensusChartByMonth(census);
    }



    /**
     * 党委信息
     * @param mineCommittee
     * @return
     */

    public MineCommittee selectCommitteeInfo(MineCommittee mineCommittee){
        return this.baseMapper.selectBaseCommitteeInfo(mineCommittee);
    }

    /**
     * 党委信息 党委支部信息
     * @param
     * @return
     */


    public List<Census> selectCommitteeBranchRank(Census census){
        return this.baseMapper.selectCommitteeBranchRank(census);
    }

    /**
     * 所有信息 -  支部列表
     */
    public List<Census> selectBranchList(Census census){
        return this.baseMapper.selectBranchList(census);
    }


    public List<Census> selectMemberRank(Census census){
        return this.baseMapper.selectMemberRank(census);
    }


    public List<Census> selectMemberRankSingle(Census census){
        return this.baseMapper.selectMemberRankSingle(census);
    }

    /***/
    public Census selectActivityAllCensusCommunity(Census census){
        return baseMapper.selectActivityAllCensusCommunity(census);
    }

    public Census selectActivityAllCensusPerson(Census census){
        return baseMapper.selectActivityAllCensusPerson(census);
    }

    /***/
    public Census selectWishAllCensusCommunity(Census census){
        return baseMapper.selectWishAllCensusCommunity(census);
    }

    public Census selectWishAllCensusPerson(Census census){
        return baseMapper.selectWishAllCensusPerson(census);
    }


    public List<Census> selectActivityType(Census census){
        return this.baseMapper.selectActivityType(census);
    }
    public List<Census> selectWishType(Census census){
        return this.baseMapper.selectWishType(census);
    }



    public List<Census> selectRelationBranch(){
        return this.baseMapper.selectRelationBranch();
    }

    public List<Census> selectRelationCommunity(){
        return this.baseMapper.selectRelationCommunity();
    }
    public List<Census> selectRelationBranchAndCommunity(){
        return this.baseMapper.selectRelationBranchAndCommunity();
    }
    public List<Census> selectRelationBranchAndCommunityByBranchId(Census censu){
        return this.baseMapper.selectRelationBranchAndCommunityByBranchId(censu);
    }
}
