ruleset {
    ruleset('rulesets/basic.xml') {
        // OK for tests
        exclude 'ComparisonWithSelf'
    }

    ruleset('rulesets/braces.xml')

    ruleset('rulesets/concurrency.xml')

    ruleset('rulesets/convention.xml') {
        // we don't care
        exclude 'NoDef'
        // we don't care
        exclude 'TrailingComma'
    }

    ruleset('rulesets/design.xml') {
        // we don't care
        exclude 'Instanceof'
        // we don't care
        exclude 'AbstractClassWithoutAbstractMethod'
        // we don't care
        exclude 'BuilderMethodWithSideEffects'
    }

    // the DRY rules do not necessarily lead to better code
    // ruleset('rulesets/dry.xml')

    // these rules cause compilation failure warnings
    // ruleset('rulesets/enhanced.xml')

    ruleset('rulesets/exceptions.xml')

    ruleset('rulesets/formatting.xml') {
        // empty blocks like {} are OK
        SpaceAfterOpeningBrace {
            ignoreEmptyBlock = true
        }
        // enforce at least one space after map entry colon
        SpaceAroundMapEntryColon {
            characterAfterColonRegex = /\s/
            characterBeforeColonRegex = /./
        }
        // empty blocks like {} are OK
        SpaceBeforeClosingBrace {
            ignoreEmptyBlock = true
        }

        // we don't care
        exclude 'ClassJavadoc'
    }

    ruleset('rulesets/generic.xml')

    ruleset('rulesets/groovyism.xml') {
        // we don't care
        exclude 'GStringExpressionWithinString'
    }

    ruleset('rulesets/imports.xml') {
        // we order static imports after other imports because that's the default style in IDEA
        MisorderedStaticImports {
            comesBefore = false
        }
    }

    ruleset('rulesets/junit.xml') {
        // Spock ...
        exclude 'JUnitPublicNonTestMethod'
        // REST Assured
        exclude 'JUnitTestMethodWithoutAssert'
    }

    ruleset('rulesets/logging.xml')

    ruleset('rulesets/naming.xml') {
        // we don't care
        exclude 'FactoryMethodName'
        // we don't care
        exclude 'MethodName'
    }

    ruleset('rulesets/security.xml') {
        // we don't care because our classes need not to satisfy the Java Beans specification
        exclude 'JavaIoPackageAccess'
        // we don't care for now
        exclude 'FileCreateTempFile'
    }

    ruleset('rulesets/serialization.xml')

    // we don't care for now
    // ruleset('rulesets/size.xml')

    ruleset('rulesets/unnecessary.xml') {
        // we don't care for now
        exclude 'UnnecessaryObjectReferences'
    }

    ruleset('rulesets/unused.xml')
}
