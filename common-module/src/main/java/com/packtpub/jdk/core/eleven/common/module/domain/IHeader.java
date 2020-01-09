
package com.packtpub.jdk.core.eleven.common.module.domain;

/**
 * @author dbatista
 */
@FunctionalInterface
public interface IHeader<T1, T2> {
    String apply(T1 token, T2 apiKey);
}