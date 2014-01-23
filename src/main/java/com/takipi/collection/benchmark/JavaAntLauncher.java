package com.takipi.collection.benchmark;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Java;
import org.apache.tools.ant.types.Path;

public class JavaAntLauncher
{
	static void testClass(String className)
	{
		Project project = new Project();
		project.setBaseDir(new File(System.getProperty("user.dir")));
		project.init();
		DefaultLogger logger = new DefaultLogger();
		project.addBuildListener(logger);
		logger.setOutputPrintStream(System.out);
		logger.setErrorPrintStream(System.err);
		logger.setMessageOutputLevel(Project.MSG_INFO);
		// System.setOut(new PrintStream(new DemuxOutputStream(project, false)));
		// System.setErr(new PrintStream(new DemuxOutputStream(project, true)));
		project.fireBuildStarted();
		
		System.out.println("running");
		Throwable caught = null;
		
		try
		{
			
			Java javaTask = new Java();
			javaTask.setTaskName("runjava");
			javaTask.setProject(project);
			javaTask.setFork(true);
			javaTask.setMaxmemory(Properties.MAX_MEMORY);
			javaTask.setFailonerror(true);
			javaTask.setClassname(TestPerformer.class.getName());
			javaTask.getCommandLine().createArgument().setLine(className);
			
			//get classpath from parent programm
			Path path = new Path(project);
			String classpath = System.getProperty("java.class.path");
			String[] classpathEntries = classpath.split(File.pathSeparator);
			
			for (int i = 0; i < classpathEntries.length; i++)
			{
				path.add(new Path(project, new File(classpathEntries[i]).getAbsolutePath()));
				
			}
			
			javaTask.setClasspath(path);
			javaTask.init();
			
			int ret = javaTask.executeJava();
			System.out.println("java task return code: " + ret);
		}
		catch (BuildException e)
		{
			caught = e;
		}
		
		project.log("finished");
		project.fireBuildFinished(caught);
	}
}
