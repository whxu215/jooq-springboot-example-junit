/*
 * This file is generated by jOOQ.
 */
package com.github.jooq.example.gen;


import com.github.jooq.example.gen.tables.Databasechangelog;
import com.github.jooq.example.gen.tables.Databasechangeloglock;
import com.github.jooq.example.gen.tables.User;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in test
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>test.databasechangelog</code>.
     */
    public static final Databasechangelog DATABASECHANGELOG = Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>test.databasechangeloglock</code>.
     */
    public static final Databasechangeloglock DATABASECHANGELOGLOCK = Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * 管理员信息
     */
    public static final User USER = User.USER;
}
