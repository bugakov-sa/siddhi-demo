import java.nio.file.Paths

import org.wso2.siddhi.core.SiddhiManager
import org.wso2.siddhi.core.event.Event
import org.wso2.siddhi.core.query.output.callback.QueryCallback

import scala.io.Source

object Application extends App {

  lazy val script = Source.fromFile(
    Paths.get(
      "..",
      "conf",
      "script.txt"
    ).toFile, "UTF-8"
  ).mkString

  lazy val events = Source.fromFile(
    Paths.get(
      "..",
      "conf",
      "events.txt"
    ).toFile, "UTF-8"
  ).getLines
    .map(s => Array[AnyRef](s.toString))

  val runtime = new SiddhiManager().createExecutionPlanRuntime(script)
  runtime.addCallback("output", new QueryCallback {
    override def receive(
                          timeStamp: Long,
                          inEvents: Array[Event],
                          removeEvents: Array[Event]
                        ): Unit = {
      inEvents.foreach(e => {
        println("event:" + e.getData.mkString("{", ";", "}"))
      })
    }
  })
  val input = runtime.getInputHandler("input")
  runtime.start()
  events.foreach(input.send)
  runtime.shutdown()
}