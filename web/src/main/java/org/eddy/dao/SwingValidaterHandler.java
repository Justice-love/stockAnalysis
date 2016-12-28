package org.eddy.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.eddy.swing.entity.Validater;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eddy on 2016/12/28.
 */
public class SwingValidaterHandler extends BaseTypeHandler<Validater> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Validater parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public Validater getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (StringUtils.isBlank(rs.getString(columnName))) return null;
        return Validater.valueOf(rs.getString(columnName));
    }

    @Override
    public Validater getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (StringUtils.isBlank(rs.getString(columnIndex))) return null;
        return Validater.valueOf(rs.getString(columnIndex));
    }

    @Override
    public Validater getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (StringUtils.isBlank(cs.getString(columnIndex))) return null;
        return Validater.valueOf(cs.getString(columnIndex));
    }
}
