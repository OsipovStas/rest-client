import dispatch.Http
import java.text.SimpleDateFormat
import java.util.Date

object Rest extends App {

  import dispatch._, Defaults._

  val Format = new SimpleDateFormat("yyyy-MM-dd")

  implicit def string2date(date: String): Date = Format.parse(date)

  Seq(
    RawMatch("Inter", "Genoa", 2, 0, "2013-08-23"),
    RawMatch("Napoli", "Bolonya", 2, 1, "2013-10-16"),
    RawMatch("Livorno", "Roma", 0, 3, "2013-08-23"),
    RawMatch("Torino", "Juventurs", 0, 0, "2013-08-23")
  )

  val rm = RawMatch("Inter", "Genoa", 2, 0, "2013-08-23")
  val rt = RawTask("Zenit", 1, "2013-02-11")

  println(JAXBWrappers.raw2XMLString(rm))
  println(JAXBWrappers.raw2XMLString(rt))

  RestClient.sendTask(rt).onComplete {
    r =>
      println(r.get)
  }

  Thread.sleep(3000)
  Http.shutdown()
}

object RestClient {

  import dispatch._, Defaults._

  val host = url("http://desolate-everglades-5929.herokuapp.com")

  val putMatch = (host / "match").PUT.setHeader("Content-Type", "text/xml; charset=UTF-8")

  val putTask = (host / "task").PUT.setHeader("Content-Type", "text/xml; charset=UTF-8").setHeader("Accept", "text/javascript")

  def sendMatch(rm: RawMatch) = {
    val req: Req = putMatch.setBody(JAXBWrappers.raw2XMLString(rm))
    Http(req OK as.String)
  }

  def sendTask(rt: RawTask) = {
    val req: Req = putTask.setBody(JAXBWrappers.raw2XMLString(rt))
    Http(req OK as.String)
  }
}
