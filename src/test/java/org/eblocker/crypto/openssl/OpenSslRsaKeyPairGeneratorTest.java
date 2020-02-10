/*
 * Copyright 2020 eBlocker Open Source UG (haftungsbeschraenkt)
 *
 * Licensed under the EUPL, Version 1.2 or - as soon they will be
 * approved by the European Commission - subsequent versions of the EUPL
 * (the "License"); You may not use this work except in compliance with
 * the License. You may obtain a copy of the License at:
 *
 *   https://joinup.ec.europa.eu/page/eupl-text-11-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.eblocker.crypto.openssl;

import org.junit.Assert;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class OpenSslRsaKeyPairGeneratorTest {

    @Test
    public void testGeneration() {
        KeyPairGenerator generator = new OpenSslRsaKeyPairGenerator();

        int[] keySizes = new int[] { 512, 1024, 2048, 4096 };
        for(int keySize : keySizes) {
            generator.initialize(keySize);
            KeyPair pair = generator.generateKeyPair();
            Assert.assertEquals("RSA", pair.getPrivate().getAlgorithm());
            Assert.assertEquals("RSA", pair.getPublic().getAlgorithm());
            Assert.assertEquals(keySize, ((RSAPrivateKey)pair.getPrivate()).getModulus().bitLength());
            Assert.assertEquals(keySize, ((RSAPublicKey)pair.getPublic()).getModulus().bitLength());
        }
    }

}