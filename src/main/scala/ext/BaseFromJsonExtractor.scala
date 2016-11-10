package ext

import org.wso2.siddhi.core.config.ExecutionPlanContext
import org.wso2.siddhi.core.executor.ExpressionExecutor
import org.wso2.siddhi.core.executor.function.FunctionExecutor
import play.api.libs.json.{JsLookupResult, Json}

abstract class BaseFromJsonExtractor extends FunctionExecutor {

  override def execute(args: Array[AnyRef]): AnyRef = {
    if (args.length < 2) {
      throw new scala.Exception
    }
    for (arg <- args) {
      if (!arg.isInstanceOf[String]) {
        throw new scala.Exception
      }
    }
    val path = (for (i <- (1 until args.length)) yield args(i) toString)
    var lookup = Json.parse(args(0) toString) \ path(0)
    for (i <- (1 until path.length)) {
      lookup \= path(i)
    }
    convertLookup(lookup)
  }

  def convertLookup(lookup: JsLookupResult): AnyRef

  override def init(args: Array[ExpressionExecutor], context: ExecutionPlanContext): Unit = {}

  override def execute(event: scala.Any): AnyRef = Nil

  override def currentState(): Array[AnyRef] = Array()

  override def restoreState(state: Array[AnyRef]): Unit = {}

  override def stop(): Unit = {}

  override def start(): Unit = {}
}
