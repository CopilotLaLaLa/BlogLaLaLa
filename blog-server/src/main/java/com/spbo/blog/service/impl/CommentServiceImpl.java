package com.spbo.blog.service.impl;

import com.spbo.blog.dao.CommentDao;
import com.spbo.blog.dao.UsermsgDao;
import com.spbo.blog.domain.*;
import com.spbo.blog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    public static boolean isInt(String str) {
        try {
             Integer.valueOf(str);
             return true;
        } catch (Exception e){
          return false;
        }
    }

    @Resource
    UsermsgDao UsermsgDao;

    @Resource
    CommentDao CommentDao;

    @Override
    public List<CommentWithBLOBs> getComment(Integer Aid) {
        List<CommentWithBLOBs> allComment = CommentDao.selectByArticleKey(Aid);
        return allComment;
    }

    @Override
    public CommentMain getCommentJson(Integer Aid, Integer Page) {
        List<CommentWithBLOBs> allComment = CommentDao.selectByArticlePageKey(Aid,(Page-1)*6);
        int commentSize = allComment.size();
        List<CommentJson> cj = new ArrayList<>();
        Iterator<CommentWithBLOBs> it = allComment.iterator();
        while (it.hasNext()) {
            CommentWithBLOBs cb = (CommentWithBLOBs) it.next();
            CommentJson tcb = new CommentJson();
            if(!cb.getCmischild()) {
                tcb.setUsername(UsermsgDao.selectByPrimaryKey(cb.getCmuser()).getUsername());
                tcb.setUserHig(UsermsgDao.selectByPrimaryKey(cb.getCmuser()).getUserhig());
                tcb.setCmb(cb);
                if(cb.getCmrp() != null){
                    String[] cr = cb.getCmrp().split(",");
                    if (isInt(cr[0])){
                        List<CommentJson> childCm = new ArrayList<>();;
                        for (int i = 0; i < cr.length; i++) {
                            if (cr[i] != null) {
                                CommentJson temp = new CommentJson();
                                CommentWithBLOBs childComment = CommentDao.selectByPrimaryKey(Integer.parseInt(cr[i]));
                                temp.setCmb(childComment);
                                temp.setUsername(UsermsgDao.selectByPrimaryKey(childComment.getCmuser()).getUsername());
                                temp.setUserHig(UsermsgDao.selectByPrimaryKey(childComment.getCmuser()).getUserhig());
                                childCm.add(temp);
                            }
                        }
                        tcb.setChildCm(childCm);
                    }
                }
                cj.add(tcb);
            }
        }
        CommentMain result = new CommentMain();
        result.setCommentText(cj);
        result.setCommentsNumber(CommentDao.selectCommentAll(Aid));
        result.setCommentsMain(commentSize);
        result.setCommentsAllMain(CommentDao.selectCommentMain(Aid));
        return result;
    }

    @Override
    public Integer getCommentNum(Integer Aid) {
        List<CommentWithBLOBs> allComment = CommentDao.selectByArticleKey(Aid);
        int commentSize = allComment.size();
        return commentSize;
    }

    @Override
    public boolean insertSelective(UpdataComment record){
        try {
            CommentWithBLOBs child = new CommentWithBLOBs();
            child = record.getChildMsg();
            CommentDao.insertSelective(child);
            if(record.getIdcomment() != 0){
                String add = child.getIdcomment()+",";
                CommentDao.updateByCmrp(add ,record.getIdcomment());
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteSelective(Integer id,Integer isChild){
        if(isChild != 0){
            try {
                CommentDao.deleteByPrimaryKey(id);
                String rid = id + ",";
                CommentDao.removeByCmrp(rid, isChild);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        else {
            try {
                if(CommentDao.selectByPrimaryKey(id).getCmrp() != null){
                    String[] deleteChild = CommentDao.selectByPrimaryKey(id).getCmrp().split(",");
                    for (int i = 0; i < deleteChild.length; i++) {
                        if (isInt(deleteChild[i]))
                        {
                            CommentDao.deleteByPrimaryKey(Integer.parseInt(deleteChild[i]));
                        }
                    }
                    CommentDao.deleteByPrimaryKey(id);
                    return true;
                }
                else {
                    CommentDao.deleteByPrimaryKey(id);
                    return true;
                }
            }catch (Exception e){
                return false;
            }
        }
    }
}
