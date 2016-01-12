package com.vxml.core;

public class Output {
    private OutputType outputType;
    private String output;
    
    @Override
    public String toString() {
        return getOutputType() + ":" + getOutput();
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
