package typings.undiciTypes.mod

import typings.undiciTypes.websocketMod.MessageEvent
import typings.undiciTypes.websocketMod.MessageEventInit
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
@JSImport("undici-types", "MessageEvent")
@js.native
open class MessageEventCls[T] protected ()
  extends StObject
     with MessageEvent[T] {
  def this(`type`: String) = this()
  def this(`type`: String, eventInitDict: MessageEventInit[T]) = this()
  
  /* CompleteClass */
  override val data: T = js.native
  
  /* CompleteClass */
  override def initMessageEvent(
    `type`: String,
    bubbles: js.UndefOr[Boolean],
    cancelable: js.UndefOr[Boolean],
    data: js.UndefOr[Any],
    origin: js.UndefOr[String],
    lastEventId: js.UndefOr[String],
    source: js.UndefOr[
      (/* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof MessagePort */ Any) | Null
    ],
    ports: js.UndefOr[
      js.Array[
        /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof MessagePort */ Any
      ]
    ]
  ): Unit = js.native
  
  /* CompleteClass */
  override val lastEventId: String = js.native
  
  /* CompleteClass */
  override val origin: String = js.native
  
  /* CompleteClass */
  override val ports: js.Array[
    /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof MessagePort */ Any
  ] = js.native
  
  /* CompleteClass */
  override val source: (/* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof MessagePort */ Any) | Null = js.native
}
