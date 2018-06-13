package com.thaumcraft.nodes;

import com.thaumcraft.aspects.Aspect;
import com.thaumcraft.aspects.AspectList;
import com.thaumcraft.aspects.IAspectContainer;

public interface INode extends IAspectContainer {

	/**
	 * Unique identifier to distinguish nodes. Normal node id's are based on world id and coordinates
	 * @return
	 */
	public String getId();
	
	public AspectList getAspectsBase();
	
	/**
	 * Return the type of node
	 * @return
	 */
	public NodeType getNodeType();

	/**
	 * Set the type of node
	 * @return
	 */
	public void setNodeType(NodeType nodeType);

	/**
	 * Set the node modifier
	 * @return
	 */
	public void setNodeModifier(NodeModifier nodeModifier);
	
	/**
	 * Return the node modifier
	 * @return
	 */
	public NodeModifier getNodeModifier();
		
	/**
	 * Return the maximum capacity of each aspect the node can hold
	 * @return
	 */
	public int getNodeVisBase(Aspect aspect);

	/**
	 * Set the maximum capacity of each aspect the node can hold
	 * @return
	 */
	public void setNodeVisBase(Aspect aspect, short nodeVisBase);
	
}
