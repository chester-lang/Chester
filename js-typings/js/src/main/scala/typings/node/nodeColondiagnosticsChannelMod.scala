package typings.node

import typings.node.diagnosticsChannelMod.ChannelListener
import typings.node.diagnosticsChannelMod.TracingChannelCollection
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object nodeColondiagnosticsChannelMod {
  
  @JSImport("node:diagnostics_channel", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * The class `Channel` represents an individual named channel within the data
    * pipeline. It is used to track subscribers and to publish messages when there
    * are subscribers present. It exists as a separate object to avoid channel
    * lookups at publish time, enabling very fast publish speeds and allowing
    * for heavy use while incurring very minimal cost. Channels are created with {@link channel}, constructing a channel directly
    * with `new Channel(name)` is not supported.
    * @since v15.1.0, v14.17.0
    */
  @JSImport("node:diagnostics_channel", "Channel")
  @js.native
  open class Channel_[StoreType, ContextType] protected ()
    extends typings.node.diagnosticsChannelMod.Channel_[StoreType, ContextType] {
    /* private */ def this(name: String) = this()
    /* private */ def this(name: js.Symbol) = this()
  }
  
  /**
    * The class `TracingChannel` is a collection of `TracingChannel Channels` which
    * together express a single traceable action. It is used to formalize and
    * simplify the process of producing events for tracing application flow. {@link tracingChannel} is used to construct a `TracingChannel`. As with `Channel` it is recommended to create and reuse a
    * single `TracingChannel` at the top-level of the file rather than creating them
    * dynamically.
    * @since v19.9.0
    * @experimental
    */
  @JSImport("node:diagnostics_channel", "TracingChannel")
  @js.native
  open class TracingChannel_[StoreType, ContextType /* <: js.Object */] ()
    extends typings.node.diagnosticsChannelMod.TracingChannel_[StoreType, ContextType]
  
  /**
    * This is the primary entry-point for anyone wanting to publish to a named
    * channel. It produces a channel object which is optimized to reduce overhead at
    * publish time as much as possible.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * const channel = diagnostics_channel.channel('my-channel');
    * ```
    * @since v15.1.0, v14.17.0
    * @param name The channel name
    * @return The named channel object
    */
  inline def channel(name: String): typings.node.diagnosticsChannelMod.Channel_[Any, Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("channel")(name.asInstanceOf[js.Any]).asInstanceOf[typings.node.diagnosticsChannelMod.Channel_[Any, Any]]
  inline def channel(name: js.Symbol): typings.node.diagnosticsChannelMod.Channel_[Any, Any] = ^.asInstanceOf[js.Dynamic].applyDynamic("channel")(name.asInstanceOf[js.Any]).asInstanceOf[typings.node.diagnosticsChannelMod.Channel_[Any, Any]]
  
  /**
    * Check if there are active subscribers to the named channel. This is helpful if
    * the message you want to send might be expensive to prepare.
    *
    * This API is optional but helpful when trying to publish messages from very
    * performance-sensitive code.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * if (diagnostics_channel.hasSubscribers('my-channel')) {
    *   // There are subscribers, prepare and publish message
    * }
    * ```
    * @since v15.1.0, v14.17.0
    * @param name The channel name
    * @return If there are active subscribers
    */
  inline def hasSubscribers(name: String): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("hasSubscribers")(name.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  inline def hasSubscribers(name: js.Symbol): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("hasSubscribers")(name.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Register a message handler to subscribe to this channel. This message handler
    * will be run synchronously whenever a message is published to the channel. Any
    * errors thrown in the message handler will trigger an `'uncaughtException'`.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * diagnostics_channel.subscribe('my-channel', (message, name) => {
    *   // Received data
    * });
    * ```
    * @since v18.7.0, v16.17.0
    * @param name The channel name
    * @param onMessage The handler to receive channel messages
    */
  inline def subscribe(name: String, onMessage: ChannelListener): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("subscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def subscribe(name: js.Symbol, onMessage: ChannelListener): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("subscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Unit]
  
  /**
    * Creates a `TracingChannel` wrapper for the given `TracingChannel Channels`. If a name is given, the corresponding tracing
    * channels will be created in the form of `tracing:${name}:${eventType}` where `eventType` corresponds to the types of `TracingChannel Channels`.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * const channelsByName = diagnostics_channel.tracingChannel('my-channel');
    *
    * // or...
    *
    * const channelsByCollection = diagnostics_channel.tracingChannel({
    *   start: diagnostics_channel.channel('tracing:my-channel:start'),
    *   end: diagnostics_channel.channel('tracing:my-channel:end'),
    *   asyncStart: diagnostics_channel.channel('tracing:my-channel:asyncStart'),
    *   asyncEnd: diagnostics_channel.channel('tracing:my-channel:asyncEnd'),
    *   error: diagnostics_channel.channel('tracing:my-channel:error'),
    * });
    * ```
    * @since v19.9.0
    * @experimental
    * @param nameOrChannels Channel name or object containing all the `TracingChannel Channels`
    * @return Collection of channels to trace with
    */
  inline def tracingChannel[StoreType, ContextType /* <: js.Object */](nameOrChannels: String): typings.node.diagnosticsChannelMod.TracingChannel_[StoreType, ContextType] = ^.asInstanceOf[js.Dynamic].applyDynamic("tracingChannel")(nameOrChannels.asInstanceOf[js.Any]).asInstanceOf[typings.node.diagnosticsChannelMod.TracingChannel_[StoreType, ContextType]]
  inline def tracingChannel[StoreType, ContextType /* <: js.Object */](nameOrChannels: TracingChannelCollection[StoreType, ContextType]): typings.node.diagnosticsChannelMod.TracingChannel_[StoreType, ContextType] = ^.asInstanceOf[js.Dynamic].applyDynamic("tracingChannel")(nameOrChannels.asInstanceOf[js.Any]).asInstanceOf[typings.node.diagnosticsChannelMod.TracingChannel_[StoreType, ContextType]]
  
  /**
    * Remove a message handler previously registered to this channel with {@link subscribe}.
    *
    * ```js
    * import diagnostics_channel from 'node:diagnostics_channel';
    *
    * function onMessage(message, name) {
    *   // Received data
    * }
    *
    * diagnostics_channel.subscribe('my-channel', onMessage);
    *
    * diagnostics_channel.unsubscribe('my-channel', onMessage);
    * ```
    * @since v18.7.0, v16.17.0
    * @param name The channel name
    * @param onMessage The previous subscribed handler to remove
    * @return `true` if the handler was found, `false` otherwise.
    */
  inline def unsubscribe(name: String, onMessage: ChannelListener): Boolean = (^.asInstanceOf[js.Dynamic].applyDynamic("unsubscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Boolean]
  inline def unsubscribe(name: js.Symbol, onMessage: ChannelListener): Boolean = (^.asInstanceOf[js.Dynamic].applyDynamic("unsubscribe")(name.asInstanceOf[js.Any], onMessage.asInstanceOf[js.Any])).asInstanceOf[Boolean]
}
