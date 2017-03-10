package com.hm

import java.sql.{Connection,DriverManager,ResultSet}



/**
  * Created by rahul on 6/3/17.
  */
object MySqlClient {


  private val dbc = "jdbc:mysql://" + "127.0.0.1" + ":" + 3306 + "/" + "rest" + "?user=" + "root" + "&password=" + "root"
  classOf[com.mysql.jdbc.Driver]
  private var conn: Connection = DriverManager.getConnection(dbc)

  def getConnection: Connection = {
    if (conn.isClosed) {
      conn = DriverManager.getConnection(dbc)
    }
    conn
  }

  def closeConnection() = conn.close()

  def executeQuery(query: String): Boolean = {
    val statement = getConnection.createStatement()
    try
      statement.execute(query)
    finally statement.close()
  }

  def getResultSet(query: String): ResultSet={
    val statement=getConnection.createStatement()
    statement.executeQuery(query)
  }


}
