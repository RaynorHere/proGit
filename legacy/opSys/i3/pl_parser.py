#!/usr/bin/env python

from pl_syntaxexception import SyntaxException
from pl_node import *
from pl_scanner import Scanner
from pl_token import Token


class Parser(object):
    """ generated source for class Parser """
    def __init__(self):
        self.scanner = None

    def match(self, s):
        """ generated source for method match """
        self.scanner.match(Token(s))

    def curr(self):
        """ generated source for method curr """
        return self.scanner.curr()

    def pos(self):
        """ generated source for method pos """
        return self.scanner.position()

    def parseBlock(self):
        """ generated source for method parseBlock """
        stmt = self.parseStmt()
        rest = None
        if self.curr() == Token(";"):
            self.match(";")
            rest = self.parseBlock()
        block = NodeBlock(stmt, rest)
        return block

    def parseStmt(self):
        """ generated source for method parseStmt """
        if self.curr() == Token("id"):
            assn = self.parseAssn()
            return NodeStmt(assn)
        if self.curr() == Token("wr"):
            wr = self.parseWr()
            return NodeStmt(wr)
        return None

    def parseWr(self):
        self.match("wr")
        expr = self.parseExpr()
        wr = NodeWr(expr)
        return wr

    def parseAssn(self):
        """ generated source for method parseAssn """
        nid = self.curr()
        self.match("id")
        self.match("=")
        expr = self.parseExpr()
        assn = NodeAssn(nid.lex(), expr)
        return assn

    def parseExpr(self):
        term = self.parseTerm()
        addop = self.parseAddop()
        if addop is None:
            return NodeExpr(term, None, None)
        expr = self.parseExpr()
        expr.append(NodeExpr(term, addop, None))
        return expr

    def parseTerm(self):
        fact = self.parseFact()
        mulop = self.parseMulop()
        if mulop is None:
            return NodeTerm(fact, None, None)
        term = self.parseTerm()
        term.append(NodeTerm(fact, mulop, None))
        return term

    def parseFact(self):
        if self.curr() == Token("("):
            self.match("(")
            expr = self.parseExpr()
            self.match(")")
            return NodeFact("expression", expr, None, None)
        if self.curr() == Token("-"):
            self.match("-")
            fact = self.parseFact()
            return NodeFact("factor", fact, None, None)
        if self.curr() == Token("id"):
            nid = self.curr()
            self.match("id")
            if self.curr() == Token("("):
                self.match("(")
                expr = self.parseExpr()
                self.match(")")
                return NodeFact("call", self.pos(), nid.lex(), expr)
            return NodeFact("id", self.pos(), nid.lex(), None)
        num = self.curr()
        self.match("num")
        return NodeFact("num", num.lex(), None, None)

    def parseAddop(self):
        if self.curr() == Token("+"):
            self.match("+")
            return NodeAddop(self.pos(), "+")
        if self.curr() == Token("-"):
            self.match("-")
            return NodeAddop(self.pos(), "-")
        return None

    def parseMulop(self):
        if self.curr() == Token("*"):
            self.match("*")
            return NodeMulop(self.pos(), "*")
        if self.curr() == Token("/"):
            self.match("/")
            return NodeMulop(self.pos(), "/")
        return None

    def parse(self, program):
        """ generated source for method parse """
        if program == '': return None
        self.scanner = Scanner(program)
        self.scanner.next()
        return self.parseBlock()

