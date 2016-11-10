package ext

import org.wso2.siddhi.query.api.definition.Attribute
import org.wso2.siddhi.query.api.definition.Attribute.Type
import play.api.libs.json._

class IntFromJsonExtractor extends BaseFromJsonExtractor {
  override def convertLookup(lookup: JsLookupResult): AnyRef = {
    lookup.asOpt[Int] match {
      case None => throw new scala.Exception
      case Some(v) => v.asInstanceOf[AnyRef]
    }
  }

  override def getReturnType: Type = Attribute.Type.INT
}
