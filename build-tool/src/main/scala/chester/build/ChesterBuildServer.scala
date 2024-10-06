package chester.build

import ch.epfl.scala.bsp4j._
import java.util.concurrent.CompletableFuture
import java.util.Collections
import scala.jdk.CollectionConverters._

class ChesterBuildServer extends BuildServer {

  private var client: BuildClient = scala.compiletime.uninitialized

  // Implementing onConnectWithClient from BuildClientAware interface
   def onConnectWithClient(client: BuildClient): Unit = {
    this.client = client
    // Additional setup if needed
  }

  // BuildServer methods

  override def buildInitialize(params: InitializeBuildParams): CompletableFuture[InitializeBuildResult] = {
    val capabilities = new BuildServerCapabilities()
    capabilities.setCompileProvider(new CompileProvider(Collections.singletonList("chester")))
    capabilities.setTestProvider(new TestProvider(Collections.singletonList("chester")))
    capabilities.setRunProvider(new RunProvider(Collections.singletonList("chester")))
    // Set other capabilities as needed

    val result = new InitializeBuildResult(
      "Chester Build Server",
      "1.0.0",
      "2.0.0",
      capabilities
    )
    CompletableFuture.completedFuture(result)
  }

  override def onBuildInitialized(): Unit = {
    // Initialization logic after buildInitialize
  }

  override def buildShutdown(): CompletableFuture[Object] = {
    CompletableFuture.completedFuture(null)
  }

  override def onBuildExit(): Unit = {
    // Cleanup resources if needed
    System.exit(0)
  }

  override def workspaceBuildTargets(): CompletableFuture[WorkspaceBuildTargetsResult] = {
    // Return the list of build targets in the workspace
    CompletableFuture.failedFuture(new NotImplementedError("workspaceBuildTargets is not implemented yet"))
  }

  override def buildTargetSources(params: SourcesParams): CompletableFuture[SourcesResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetSources is not implemented yet"))
  }

  override def buildTargetInverseSources(params: InverseSourcesParams): CompletableFuture[InverseSourcesResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetInverseSources is not implemented yet"))
  }

  override def buildTargetDependencySources(params: DependencySourcesParams): CompletableFuture[DependencySourcesResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetDependencySources is not implemented yet"))
  }

  override def buildTargetResources(params: ResourcesParams): CompletableFuture[ResourcesResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetResources is not implemented yet"))
  }

  override def buildTargetCompile(params: CompileParams): CompletableFuture[CompileResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetCompile is not implemented yet"))
  }

  override def buildTargetTest(params: TestParams): CompletableFuture[TestResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetTest is not implemented yet"))
  }

  override def buildTargetRun(params: RunParams): CompletableFuture[RunResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetRun is not implemented yet"))
  }

  override def buildTargetCleanCache(params: CleanCacheParams): CompletableFuture[CleanCacheResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetCleanCache is not implemented yet"))
  }

  // Implementing missing methods from BuildServer interface

  override def buildTargetDependencyModules(params: DependencyModulesParams): CompletableFuture[DependencyModulesResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetDependencyModules is not implemented yet"))
  }

  override def buildTargetOutputPaths(params: OutputPathsParams): CompletableFuture[OutputPathsResult] = {
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetOutputPaths is not implemented yet"))
  }

  override def debugSessionStart(params: DebugSessionParams): CompletableFuture[DebugSessionAddress] = {
    CompletableFuture.failedFuture(new NotImplementedError("debugSessionStart is not implemented yet"))
  }

  override def onRunReadStdin(params: ReadParams): Unit = {
    throw new NotImplementedError("onRunReadStdin is not implemented yet")
  }

  override def workspaceReload(): CompletableFuture[Object] = {
    CompletableFuture.failedFuture(new NotImplementedError("workspaceReload is not implemented yet"))
  }

  // Additional methods as needed
}