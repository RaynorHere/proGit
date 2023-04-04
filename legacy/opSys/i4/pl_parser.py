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

    def parseProg(self):
        block = self.parseBlock()
        if not self.scanner.done():
            raise SyntaxException(self.pos(), Token("EOF"), self.curr())
        retVal = NodeProg(block)
        return retVal

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
        if self.curr() == Token("id"):
            assn = self.parseAssn()
            return NodeStmt(assn)
        if self.curr() == Token("rd"):
            rd = self.parseRd()
            return NodeStmt(rd)
        if self.curr() == Token("wr"):
            wr = self.parseWr()
            return NodeStmt(wr)
        if self.curr() == Token("if"):
            ifelse = self.parseIf()
            return NodeStmt(ifelse)
        if self.curr() == Token("while"):
            whiledo = self.parseWhile()
            return NodeStmt(whiledo)
        begin = self.parseBegin()
        return NodeStmt(begin)

    def parseRd(self):
        self.match("rd")
        nid = self.curr()
        self.match("id")
        retVal = NodeRd(nid.lex())
        return retVal

    def parseWr(self):
        """ generated source for method parseWr """
        self.match("wr")
        expr = self.parseExpr()
        wr = NodeWr(expr)
        return wr

    def parseIf(self):
        self.match("if")
        boolexpr = self.parseBoolExpr()
        self.match("then")
        thenstmt = self.parseStmt()
        elsestmt = None
        if self.curr() == Token("else"):
            self.match("else")
            elsestmt = self.parseStmt()
        retVal = NodeIf(boolexpr, thenstmt, elsestmt)
        return retVal

    def parseWhile(self):
        self.match("while")
        boolexpr = self.parseBoolExpr()
        self.match("do")
        stmt = self.parseStmt()
        retVal = NodeWhile(boolexpr, stmt)
        return retVal

    def parseBegin(self):
        self.match("begin")
        block = self.parseBlock()
        self.match("end")
        retVal = NodeBegin(block)
        return retVal

    def parseAssn(self):
        """ generated source for method parseAssn """
        nid = self.curr()
        self.match("id")
        self.match("=")
        expr = self.parseExpr()
        assn = NodeAssn(nid.lex(), expr)
        return assn

    def parseExpr(self):
        """ generated source for method parseExpr """
        term = self.parseTerm()
        addop = self.parseAddop()
        if addop is None:
            return NodeExpr(term, None, None)
        expr = self.parseExpr()
        expr.append(NodeExpr(term, addop, None))
        return expr

    def parseTerm(self):
        """ generated source for method parseTerm """
        fact = self.parseFact()
        mulop = self.parseMulop()
        if mulop is None:
            return NodeTerm(fact, None, None)
        term = self.parseTerm()
        term.append(NodeTerm(fact, mulop, None))
        return term

    def parseFact(self):
        """ generated source for method parseFact """
        if self.curr() == Token("("):
            self.match("(")
            expr = self.parseExpr()
            self.match(")")
            return NodeFactExpr(expr)
        if self.curr() == Token("-"):
            self.match("-")
            fact = self.parseFact()
            return NodeFactFact(fact)
        if self.curr() == Token("id"):
            nid = self.curr()
            self.match("id")
            if self.curr() == Token("("):
                self.match("(")
                expr = self.parseExpr()
                self.match(")")
                return NodeFuncCall(self.pos(), nid.lex(), expr)
            return NodeFactId(self.pos(), nid.lex())
        num = self.curr()
        self.match("num")
        return NodeFactNum(num.lex())

    def parseBoolExpr(self):
        exprleft = self.parseExpr()
        relop = self.parseRelop()
        exprright = self.parseExpr()
        boolexpr = NodeBoolExpr(exprleft, relop, exprright)
        return boolexpr

    def parseAddop(self):
        """ generated source for method parseAddop """
        if self.curr() == Token("+"):
            self.match("+")
            return NodeAddop(self.pos(), "+")
        if self.curr() == Token("-"):
            self.match("-")
            return NodeAddop(self.pos(), "-")
        return None

    def parseMulop(self):
        """ generated source for method parseMulop """
        if self.curr() == Token("*"):
            self.match("*")
            return NodeMulop(self.pos(), "*")
        if self.curr() == Token("/"):
            self.match("/")
            return NodeMulop(self.pos(), "/")
        return None

    def parseRelop(self):
        if self.curr() == Token("<"):
            self.match("<")
            return NodeRelop(self.pos(), "<")

        if self.curr() == Token("<="):
            self.match("<=")
            return NodeRelop(self.pos(), "<=")

        if self.curr() == Token(">"):
            self.match(">")
            return NodeRelop(self.pos(), ">")

        if self.curr() == Token(">="):
            self.match(">=")
            return NodeRelop(self.pos(), ">=")

        if self.curr() == Token("<>"):
            self.match("<>")
            return NodeRelop(self.pos(), "<>")

        if self.curr() == Token("=="):
            self.match("==")
            return NodeRelop(self.pos(), "==")
        return None

    def parse(self, program):
        """ generated source for method parse """
        self.scanner = Scanner(program)
        self.scanner.next()
        return self.parseProg()

