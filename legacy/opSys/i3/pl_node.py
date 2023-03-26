#!/usr/bin/env python
""" generated source for module Node """

#  (C) 2013 Jim Buffenbarger
#  All rights reserved.
from pl_evalexception import EvalException


class Node(object):
    """ generated source for class Node """
    pos = 0

    def __str__(self):
        """ generated source for method toString """
        result = ""
        result += str(self.__class__.__name__)
        result += " ( "
        fields = self.__dict__
        for field in fields:
            result += "  "
            result += str(field)
            result += str(": ")
            result += str(fields[field])
        result += str(" ) ")
        return result

    def eval(self, env):
        """ generated source for method eval """
        raise EvalException(self.pos, "cannot eval() node!")


class NodeBlock(Node):
    """ generated source for class NodeBlock """

    def __init__(self, stmt, block):
        """ generated source for method __init__ """
        super(NodeBlock, self).__init__()
        self.stmt = stmt
        self.block = block

    def eval(self, env):
        """ generated source for method eval """
        r = self.stmt.eval(env)
        return r if self.block is None else self.block.eval(env)


class NodeStmt(Node):
    """ generated source for class NodeStmt """

    def __init__(self, assn):
        """ generated source for method __init__ """
        super(NodeStmt, self).__init__()
        self.assn = assn

    def eval(self, env):
        return self.assn.eval(env)


class NodeWr(Node):
    def __init__(self, expr):
        super(NodeWr, self).__init__()
        self.expr = expr

    def eval(self, env):
        retVal = self.expr.eval(env)
        print(retVal)
        return retVal


class NodeAssn(Node):
    """ generated source for class NodeAssn """

    def __init__(self, id, expr):
        """ generated source for method __init__ """
        super(NodeAssn, self).__init__()
        self.id = id
        self.expr = expr

    def eval(self, env):
        """ generated source for method eval """
        return env.put(self.id, self.expr.eval(env))


class NodeExpr(Node):
    def __init__(self, term, addop, expr):
        super(NodeExpr, self).__init__()
        self.term = term
        self.addop = addop
        self.expr = expr

    def append(self, expr):
        if self.expr is None:
            self.addop = expr.addop
            self.expr = expr
            expr.addop = None
        else:
            self.expr.append(expr)

    def eval(self, env):
        return self.term.eval(env) if self.expr is None else self.addop.op(self.expr.eval(env), self.term.eval(env))


class NodeTerm(Node):
    def __init__(self, fact, mulop, term):
        super(NodeTerm, self).__init__()
        self.fact = fact
        self.mulop = mulop
        self.term = term

    def append(self, term):
        if self.term is None:
            self.mulop = term.mulop
            self.term = term
            term.mulop = None
        else:
            self.term.append(term)

    def eval(self, env):
        return self.fact.eval(env) if self.term is None else self.mulop.op(self.term.eval(env), self.fact.eval(env))


class NodeFact(Node):
    def __init__(self, factType, param1, param2, param3):
        super(NodeFact, self).__init__()
        self.factType = factType
        self.param1 = param1
        self.param2 = param2
        self.param3 = param3

    def eval(self, env):
        if self.factType == "expression":
            return self.param1.eval(env) #param1: passed in expr
        elif self.factType == "factor":
            return -self.param1.eval(env) #param1: passed in fact
        elif self.factType == "call":
            param = self.param3.eval(env) #param3: passed in pos
            f = env.getFunc(self.param1, self.param2) #param1: passed in name, param2: passed in param
            return f.call(env, param)
        elif self.factType == "id":
            return env.get(self.param1, self.param2) #param1: passed in pos, param2: passed in id
        else:
            return float(self.param1) #param1: passed in num


class NodeAddop(Node):
    def __init__(self, pos, addop):
        super(NodeAddop, self).__init__()
        self.pos = pos
        self.addop = addop

    def op(self, oper1, oper2):
        if self.addop == "+":
            return oper1 + oper2
        if self.addop == "-":
            return oper1 - oper2
        raise EvalException(self.pos, "addop: " + self.addop)


class NodeMulop(Node):
    def __init__(self, pos, mulop):
        super(NodeMulop, self).__init__()
        self.pos = pos
        self.mulop = mulop

    def op(self, oper1, oper2):
        if self.mulop == "*":
            return oper1 * oper2
        if self.mulop == "/":
            return oper1 / oper2
        raise EvalException(self.pos, "mulop: " + self.mulop)
