package com.vxml.core;

public class OutputWrapper {

    private OutputType outputType;
    private String output;

    public OutputWrapper(OutputType type, String out) {
        this.outputType = type;
        this.output = out;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

}
