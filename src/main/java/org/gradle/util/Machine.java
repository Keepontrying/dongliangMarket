package org.gradle.util;

@FunctionalInterface
interface Machine {
    public String doSomething(int id, String task);
}