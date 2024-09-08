package typings.node.streamMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * You'll have to cast your way around this structure, unfortunately.
  * TS definition: {{{
  S extends node.stream.PipelineTransformSource<infer ST> ? node.node/globals.<global>.NodeJS.WritableStream | node.stream.PipelineDestinationIterableFunction<ST> | node.stream.PipelineDestinationPromiseFunction<ST, P> : never
  }}}
  */
@js.native
trait PipelineDestination[S /* <: PipelineTransformSource[Any] */, P] extends StObject
