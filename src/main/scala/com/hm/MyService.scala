package com.hm

import java.util.Scanner

import spray.routing.HttpService
import spray.http.MediaTypes.`text/html`
import spray.json._

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readInt

/**
  * Created by rahul on 2/3/17.
  */
trait MyService extends HttpService {
  var i = true
  //val buf = ArrayBuffer(30, 34, 12, 10, 45, 1)
  while (i){
    println("\n enter choice \n 1 add\n 2 delete\n 3 test \n 4 display \n 5 exit")
    var ch =readInt()
    ch match {
      case 1 => add
      case 2 => del
      case 3 => test
      case 4 => disp
      case 5 => println("exit")
                i = false
      case _ => complete("invalid option")
    }
  }


  val myRoute =
  /*  path("test"){
      test1
    }~ path("add"){
      add1
    }~path("del"){
      del1
    }~*/path("") {
      get {
        respondWithMediaType(`text/html`) {
          // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>hello</h1>
              </body>
            </html>
          }
        }
      }
    }


    /*def add = {
      println("Enter the number")
      val a = readInt()
      buf append a
      println(buf)
    }

    def del = {
      println("Enter the number")
      val a = readInt()
      buf -= a
      println(buf)
    }

    def test = {
      println("Enter the number to search")
      val a = readInt()
      val b = buf.filter(_ < a)
      val c = buf.filter(_ > a)
      val min = b.sorted.last
      val max = c.sorted.head
      println(min + "\n" + max)
    }*/

  def disp: Unit ={
    println("the array is ")
    val rs = MySqlClient.getResultSet("select * from array")
    while (rs.next()){
      print(rs.getInt("no")+" ")
    }
    rs.close()
  }

    def add: Unit = {
      println("enter the number to add")
      val no = readInt()
      val status = MySqlClient.executeQuery("insert into array (no) values (" + no+")")
      disp
    }

    def del: Unit = {
      println("enter the number to delete")
      val no = readInt()
      val status = MySqlClient.executeQuery("delete from array where no=" + no)
      disp
    }

    def test = {
      println("enter the number to test ")
      val no = readInt()
      println(no)
      val query = "select max(no) as min1 from array where no<"+no
      println(query)
      val rs = MySqlClient.getResultSet(query)
      val rs1 = MySqlClient.getResultSet("select min(no) as max1 from array where no>"+no)
      if(rs.next() && rs1.next())
      println(rs.getInt("min1")+"\n"+rs1.getInt("max1"))
      else
      println("no match found")
      rs.close()
      rs1.close()
    }


}
