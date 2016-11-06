/**
 * Put your copyright and license info here.
 */
package com.examples.directlaunch;

import org.apache.apex.api.Launcher;
import org.apache.hadoop.conf.Configuration;

import com.datatorrent.api.Attribute;
import com.datatorrent.api.DAG;
import com.datatorrent.api.DAG.Locality;
import com.datatorrent.api.StreamingApplication;
import com.datatorrent.api.annotation.ApplicationAnnotation;
import com.datatorrent.lib.io.ConsoleOutputOperator;

@ApplicationAnnotation(name="MyFirstApplication")
public class Application implements StreamingApplication
{

  @Override
  public void populateDAG(DAG dag, Configuration conf)
  {
    // Sample DAG with 2 operators
    // Replace this code with the DAG you want to build

    RandomNumberGenerator randomGenerator = dag.addOperator("randomGenerator", RandomNumberGenerator.class);
    randomGenerator.setNumTuples(500);

    ConsoleOutputOperator cons = dag.addOperator("console", new ConsoleOutputOperator());

    dag.addStream("randomData", randomGenerator.out, cons.input).setLocality(Locality.CONTAINER_LOCAL);
  }

  public static void main(String[] args) throws Launcher.LauncherException {
    Configuration conf = new Configuration(true);
    Launcher launcher = Launcher.getLauncher(Launcher.LaunchMode.YARN);
    //Launcher launcher = Launcher.getLauncher(Launcher.LaunchMode.LOCAL);
    Attribute.AttributeMap launchAttributes = new Attribute.AttributeMap.DefaultAttributeMap();
    //launchAttributes.put(ClusterMode.LIB_JARS, "extra-transitive-dependencies-jars-for-operators");
    //launchAttributes.put(LocalMode.RUN_MILLIS, 10000L);
    launcher.launchApp(new Application(), conf, launchAttributes);
  }

}
