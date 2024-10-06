package chester.build

import ch.epfl.scala.bsp4j._
import org.log4s._
import java.util.concurrent.CompletableFuture
import java.util.Collections
import scala.jdk.CollectionConverters._

class ChesterBuildServer extends BuildServer {

  private val logger = getLogger

  private var client: BuildClient = scala.compiletime.uninitialized

  // Implementing onConnectWithClient from BuildClientAware interface
  def onConnectWithClient(client: BuildClient): Unit = {
    this.client = client
    logger.info("Build client connected to the server")
    // Additional setup if needed
  }

  // BuildServer methods

  override def buildInitialize(params: InitializeBuildParams): CompletableFuture[InitializeBuildResult] = {
    logger.info(s"Received buildInitialize request with params: $params")

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

    logger.debug(s"Build initialized with capabilities: $capabilities")
    CompletableFuture.completedFuture(result)
  }

  override def onBuildInitialized(): Unit = {
    logger.info("Build server has been initialized")
    // Initialization logic after buildInitialize
  }

  override def buildShutdown(): CompletableFuture[Object] = {
    logger.info("Received buildShutdown request")
    CompletableFuture.completedFuture(null)
  }

  override def onBuildExit(): Unit = {
    logger.info("Received onBuildExit notification")
    // Cleanup resources if needed
    System.exit(0)
  }

  override def workspaceBuildTargets(): CompletableFuture[WorkspaceBuildTargetsResult] = {
    logger.info("Received workspaceBuildTargets request")
    // Return the list of build targets in the workspace
    CompletableFuture.failedFuture(new NotImplementedError("workspaceBuildTargets is not implemented yet"))
  }

  override def buildTargetSources(params: SourcesParams): CompletableFuture[SourcesResult] = {
    logger.info(s"Received buildTargetSources request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetSources is not implemented yet"))
  }

  override def buildTargetInverseSources(params: InverseSourcesParams): CompletableFuture[InverseSourcesResult] = {
    logger.info(s"Received buildTargetInverseSources request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetInverseSources is not implemented yet"))
  }

  override def buildTargetDependencySources(params: DependencySourcesParams): CompletableFuture[DependencySourcesResult] = {
    logger.info(s"Received buildTargetDependencySources request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetDependencySources is not implemented yet"))
  }

  override def buildTargetResources(params: ResourcesParams): CompletableFuture[ResourcesResult] = {
    logger.info(s"Received buildTargetResources request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetResources is not implemented yet"))
  }

  override def buildTargetCompile(params: CompileParams): CompletableFuture[CompileResult] = {
    logger.info(s"Received buildTargetCompile request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetCompile is not implemented yet"))
  }

  override def buildTargetTest(params: TestParams): CompletableFuture[TestResult] = {
    logger.info(s"Received buildTargetTest request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetTest is not implemented yet"))
  }

  override def buildTargetRun(params: RunParams): CompletableFuture[RunResult] = {
    logger.info(s"Received buildTargetRun request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetRun is not implemented yet"))
  }

  override def buildTargetCleanCache(params: CleanCacheParams): CompletableFuture[CleanCacheResult] = {
    logger.info(s"Received buildTargetCleanCache request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetCleanCache is not implemented yet"))
  }

  // Implementing missing methods from BuildServer interface

  override def buildTargetDependencyModules(params: DependencyModulesParams): CompletableFuture[DependencyModulesResult] = {
    logger.info(s"Received buildTargetDependencyModules request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetDependencyModules is not implemented yet"))
  }

  override def buildTargetOutputPaths(params: OutputPathsParams): CompletableFuture[OutputPathsResult] = {
    logger.info(s"Received buildTargetOutputPaths request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("buildTargetOutputPaths is not implemented yet"))
  }

  override def debugSessionStart(params: DebugSessionParams): CompletableFuture[DebugSessionAddress] = {
    logger.info(s"Received debugSessionStart request with params: $params")
    CompletableFuture.failedFuture(new NotImplementedError("debugSessionStart is not implemented yet"))
  }

  override def onRunReadStdin(params: ReadParams): Unit = {
    logger.info(s"Received onRunReadStdin notification with params: $params")
    throw new NotImplementedError("onRunReadStdin is not implemented yet")
  }

  override def workspaceReload(): CompletableFuture[Object] = {
    logger.info("Received workspaceReload request")
    CompletableFuture.failedFuture(new NotImplementedError("workspaceReload is not implemented yet"))
  }

  // Additional methods as needed
}