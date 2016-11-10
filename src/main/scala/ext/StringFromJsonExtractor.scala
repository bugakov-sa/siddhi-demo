package ext

import org.wso2.siddhi.query.api.definition.Attribute
import org.wso2.siddhi.query.api.definition.Attribute.Type
import play.api.libs.json._

class StringFromJsonExtractor extends BaseFromJsonExtractor {
  override def convertLookup(lookup: JsLookupResult): String = {
    lookup.asOpt[String] match {
      case None => throw new scala.Exception
      case Some(v) => v
    }
  }

  override def getReturnType: Type = Attribute.Type.STRING
}
