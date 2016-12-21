package org.eddy.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.eddy.swing.entity.Swing;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eddy on 2016/12/21.
 */
public class SwingChildTypeHandler extends BaseTypeHandler<Swing>{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Swing parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getId());
    }

    @Override
    public Swing getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (StringUtils.isBlank(rs.getString(columnName))) return null;
        Swing swing = new Swing();
        swing.setId(rs.getString(columnName));
        return swing;
    }

    @Override
    public Swing getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (StringUtils.isBlank(rs.getString(columnIndex))) return null;
        Swing swing = new Swing();
        swing.setId(rs.getString(columnIndex));
        return swing;
    }

    @Override
    public Swing getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (StringUtils.isBlank(cs.getString(columnIndex))) return null;
        Swing swing = new Swing();
        swing.setId(cs.getString(columnIndex));
        return swing;
    }
}
