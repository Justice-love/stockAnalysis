package org.eddy.dao.mapper.swing;

import org.apache.ibatis.annotations.Mapper;
import org.eddy.swing.entity.Swing;

import java.util.List;

/**
 * Created by eddy on 2016/12/21.
 */
@Mapper
public interface StockRuleSwingMapper {

    List<Swing> selectAll();

    void insert(List<Swing> list);
}
