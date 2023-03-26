#!/usr/bin/env python

import copy

class Function(object):

    def __init__(self, args, expr):
        self.args = args
        self.expr = expr

    def call(self, env, val):
        copyEnv = copy.deepcopy(env)
        copyEnv.put(self.args, val)
        return self.expr.eval(copyEnv)