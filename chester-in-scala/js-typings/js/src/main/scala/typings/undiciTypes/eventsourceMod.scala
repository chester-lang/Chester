package typings.undiciTypes

import org.scalajs.dom.URL
import typings.undiciTypes.dispatcherMod.default
import typings.undiciTypes.patchMod.AddEventListenerOptions
import typings.undiciTypes.patchMod.Event
import typings.undiciTypes.patchMod.EventListenerOptions
import typings.undiciTypes.patchMod.EventListenerOrEventListenerObject
import typings.undiciTypes.undiciTypesInts.`0`
import typings.undiciTypes.undiciTypesInts.`1`
import typings.undiciTypes.undiciTypesInts.`2`
import typings.undiciTypes.undiciTypesStrings.error
import typings.undiciTypes.undiciTypesStrings.message
import typings.undiciTypes.undiciTypesStrings.open
import typings.undiciTypes.websocketMod.ErrorEvent
import typings.undiciTypes.websocketMod.MessageEvent
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object eventsourceMod {
  
  /* import warning: RemoveDifficultInheritance.summarizeChanges 
  - Dropped / * globalThis * / any extends {  EventTarget :infer T} ? T : {addEventListener (type : string, listener : any, options : any | undefined): void, dispatchEvent (event : undici-types.undici-types/patch.Event): boolean, removeEventListener (type : string, listener : any, options : any | boolean | undefined): void} */ @js.native
  trait EventSource extends StObject {
    
    val CLOSED: `2` = js.native
    
    val CONNECTING: `0` = js.native
    
    val OPEN: `1` = js.native
    
    def addEventListener(`type`: String, listener: EventListenerOrEventListenerObject): Unit = js.native
    def addEventListener(`type`: String, listener: EventListenerOrEventListenerObject, options: Boolean): Unit = js.native
    def addEventListener(`type`: String, listener: EventListenerOrEventListenerObject, options: AddEventListenerOptions): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_error(`type`: error, listener: js.ThisFunction1[/* this */ this.type, /* ev */ ErrorEvent, Any]): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_error(
      `type`: error,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ ErrorEvent, Any],
      options: Boolean
    ): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_error(
      `type`: error,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ ErrorEvent, Any],
      options: AddEventListenerOptions
    ): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_message(`type`: message, listener: js.ThisFunction1[/* this */ this.type, /* ev */ MessageEvent[Any], Any]): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_message(
      `type`: message,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ MessageEvent[Any], Any],
      options: Boolean
    ): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_message(
      `type`: message,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ MessageEvent[Any], Any],
      options: AddEventListenerOptions
    ): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_open(`type`: open, listener: js.ThisFunction1[/* this */ this.type, /* ev */ Event, Any]): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_open(
      `type`: open,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ Event, Any],
      options: Boolean
    ): Unit = js.native
    @JSName("addEventListener")
    def addEventListener_open(
      `type`: open,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ Event, Any],
      options: AddEventListenerOptions
    ): Unit = js.native
    
    def close(): Unit = js.native
    
    def onerror(ev: ErrorEvent): Any = js.native
    
    def onmessage(ev: MessageEvent[Any]): Any = js.native
    
    def onopen(ev: Event): Any = js.native
    
    val readyState: `0` | `1` | `2` = js.native
    
    def removeEventListener(`type`: String, listener: EventListenerOrEventListenerObject): Unit = js.native
    def removeEventListener(`type`: String, listener: EventListenerOrEventListenerObject, options: Boolean): Unit = js.native
    def removeEventListener(`type`: String, listener: EventListenerOrEventListenerObject, options: EventListenerOptions): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_error(`type`: error, listener: js.ThisFunction1[/* this */ this.type, /* ev */ ErrorEvent, Any]): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_error(
      `type`: error,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ ErrorEvent, Any],
      options: Boolean
    ): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_error(
      `type`: error,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ ErrorEvent, Any],
      options: EventListenerOptions
    ): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_message(`type`: message, listener: js.ThisFunction1[/* this */ this.type, /* ev */ MessageEvent[Any], Any]): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_message(
      `type`: message,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ MessageEvent[Any], Any],
      options: Boolean
    ): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_message(
      `type`: message,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ MessageEvent[Any], Any],
      options: EventListenerOptions
    ): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_open(`type`: open, listener: js.ThisFunction1[/* this */ this.type, /* ev */ Event, Any]): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_open(
      `type`: open,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ Event, Any],
      options: Boolean
    ): Unit = js.native
    @JSName("removeEventListener")
    def removeEventListener_open(
      `type`: open,
      listener: js.ThisFunction1[/* this */ this.type, /* ev */ Event, Any],
      options: EventListenerOptions
    ): Unit = js.native
    
    val url: String = js.native
    
    val withCredentials: Boolean = js.native
  }
  object EventSource {
    
    @JSImport("undici-types/eventsource", "EventSource.CLOSED")
    @js.native
    val CLOSED: `2` = js.native
    
    @JSImport("undici-types/eventsource", "EventSource.CONNECTING")
    @js.native
    val CONNECTING: `0` = js.native
    
    @JSImport("undici-types/eventsource", "EventSource.OPEN")
    @js.native
    val OPEN: `1` = js.native
  }
  
  /* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
  @JSImport("undici-types/eventsource", "EventSource")
  @js.native
  open class EventSourceCls protected ()
    extends StObject
       with EventSource {
    def this(url: String) = this()
    def this(url: URL) = this()
    def this(url: String, init: EventSourceInit) = this()
    def this(url: URL, init: EventSourceInit) = this()
  }
  
  trait EventSourceEventMap extends StObject {
    
    var error: ErrorEvent
    
    var message: MessageEvent[Any]
    
    var open: Event
  }
  object EventSourceEventMap {
    
    inline def apply(error: ErrorEvent, message: MessageEvent[Any], open: Event): EventSourceEventMap = {
      val __obj = js.Dynamic.literal(error = error.asInstanceOf[js.Any], message = message.asInstanceOf[js.Any], open = open.asInstanceOf[js.Any])
      __obj.asInstanceOf[EventSourceEventMap]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: EventSourceEventMap] (val x: Self) extends AnyVal {
      
      inline def setError(value: ErrorEvent): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
      
      inline def setMessage(value: MessageEvent[Any]): Self = StObject.set(x, "message", value.asInstanceOf[js.Any])
      
      inline def setOpen(value: Event): Self = StObject.set(x, "open", value.asInstanceOf[js.Any])
    }
  }
  
  trait EventSourceInit extends StObject {
    
    var dispatcher: js.UndefOr[default] = js.undefined
    
    var withCredentials: js.UndefOr[Boolean] = js.undefined
  }
  object EventSourceInit {
    
    inline def apply(): EventSourceInit = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[EventSourceInit]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: EventSourceInit] (val x: Self) extends AnyVal {
      
      inline def setDispatcher(value: default): Self = StObject.set(x, "dispatcher", value.asInstanceOf[js.Any])
      
      inline def setDispatcherUndefined: Self = StObject.set(x, "dispatcher", js.undefined)
      
      inline def setWithCredentials(value: Boolean): Self = StObject.set(x, "withCredentials", value.asInstanceOf[js.Any])
      
      inline def setWithCredentialsUndefined: Self = StObject.set(x, "withCredentials", js.undefined)
    }
  }
}
