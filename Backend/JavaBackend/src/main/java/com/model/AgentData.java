package com.model;

import java.util.List;
import java.util.Objects;

public class AgentData {
  private List<Agent> agents;

  public AgentData() {
  }

  public AgentData(List<Agent> agents) {
    this.agents = agents;
  }

  public List<Agent> getAgents() {
    return this.agents;
  }

  public void setAgents(List<Agent> agents) {
    this.agents = agents;
  }

  public AgentData agents(List<Agent> agents) {
    this.agents = agents;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AgentData)) {
            return false;
        }
        AgentData agentData = (AgentData) o;
        return Objects.equals(agents, agentData.agents);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(agents);
  }

  @Override
  public String toString() {
    return "{" +
      " agents='" + getAgents() + "'" +
      "}";
  }
  
}
