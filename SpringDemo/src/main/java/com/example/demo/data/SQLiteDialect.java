package com.example.demo.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.relational.core.dialect.AbstractDialect;
import org.springframework.data.relational.core.dialect.LimitClause;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.LockOptions;

public class SQLiteDialect extends AbstractDialect {
    private static final LimitClause LIMIT_CLAUSE = new LimitClause() {
        public String getLimit(long limit) {
            return "LIMIT " + limit;
        }

        public String getOffset(long offset) {
            return "OFFSET " + offset;
        }

        public String getLimitOffset(long limit, long offset) {
            return String.format("LIMIT %d OFFSET %d", limit, offset);
        }

        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }
    };

    public static LockClause LOCK_CLAUSE = new LockClause() {
        @Override
        public String getLock(LockOptions lockOptions) {
            return "";
        }

        @Override
        public Position getClausePosition() {
            return Position.AFTER_ORDER_BY;
        }
    };

    @Override
    public LimitClause limit() {
        return LIMIT_CLAUSE;
    }

    @Override
    public LockClause lock() {
        return LOCK_CLAUSE;
    }
}
