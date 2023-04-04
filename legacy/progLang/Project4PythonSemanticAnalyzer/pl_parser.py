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

    def parseMulop(self):
        """ generated source for method parseMulop """
        if self.curr() == Token("*"):
            self.match("*")
            return NodeMulop(self.pos(), "*")
        if self.curr() == Token("/"):
            self.match("/")
            return NodeMulop(self.pos(), "/")
        return None

    def parseAddop(self):
        """ generated source for method parseAddop """
        if self.curr() == Token("+"):
            self.match("+")
            return NodeAddop(self.pos(), "+")
        if self.curr() == Token("-"):
            self.match("-")
            return NodeAddop(self.pos(), "-")
        return None

    def parseBool(self):
        #expr relop expr
        expr1 = self.parseExpr()
        relop = self.parseRelop()
        expr2 = self.parseExpr()
        boolexp = NodeBool(expr1, relop, expr2)
        return boolexp

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
            philippe = self.curr()
            self.match("id")
            if self.curr() == Token("("):
                self.match("(")
                expr = self.parseExpr()
                self.match(")")
                return NodeFactCall(self.pos(), philippe.lex(), expr)
            return NodeFactId(self.pos(), philippe.lex())
        num = self.curr()
        self.match("num")
        return NodeFactNum(num.lex())

    def parseTerm(self):
        """ generated source for method parseTerm """
        fact = self.parseFact()
        mulop = self.parseMulop()
        if mulop == None:
            return NodeTerm(fact, None, None)
        term = self.parseTerm()
        term.append(NodeTerm(fact, mulop, None))
        return term

    def parseExpr(self):
        """ generated source for method parseExpr """
        term = self.parseTerm()
        addop = self.parseAddop()
        if addop == None:
            return NodeExpr(term, None, None)
        expr = self.parseExpr()
        expr.append(NodeExpr(term, addop, None))
        return expr

    def parseAssn(self):
        nid = self.curr()
        self.match("id")
        self.match("=")
        expr = self.parseExpr()
        assn = NodeAssn(nid.lex(), expr)
        return assn

    def parseDecl(self):
        #'def' id '(' id ')' '=' expr
        self.match("def")
        name = self.curr()
        self.match("id")
        self.match("(")
        param = self.curr()
        self.match("id")
        self.match(")")
        self.match("=")
        expr = self.parseExpr()
        dec = NodeDecl(name.lex(), param.lex(), expr)
        return dec

    def parseBeg(self):
        #'beg' block 'end'
        self.match("begin")
        block = self.parseBlock()
        beg = NodeBeg(block)
        self.match("end")
        return beg

    def parseWhile(self):
        #'while' boolexp 'do' stmt
        self.match("while")
        boolexp = self.parseBool()
        self.match("do")
        stmt = self.parseStmt()
        whil = NodeWhile(boolexp, stmt)
        return whil
    
    def parseIf(self):
        #'if' boolexp 'then' stmt
        #'if' boolexp 'then' stmt 'else' stmt
        self.match("if")
        boolexp = self.parseBool()
        self.match("then")
        stmt = self.parseStmt()
        if self.curr() == Token("else"):
            self.match("else")
            stmt2 = self.parseStmt()
            ife = NodeIfElse(boolexp, stmt, stmt2)
            return ife
        ife = NodeIf(boolexp, stmt)
        return ife

    def parseRd(self):
        #'rd' id
        self.match("rd")
        nid = self.curr()
        self.match("id")
        rd = NodeRd(nid)
        return rd

    def parseWr(self):
        """ generated source for method parseWr """
        self.match("wr")
        expr = self.parseExpr()
        wr = NodeWr(expr)
        return wr

    def parseStmt(self):
        """ generated source for method parseStmt """
        if self.curr() == Token("wr"):
            wr = self.parseWr()
            return NodeStmt(wr)
        if self.curr() == Token("id"):
            assn = self.parseAssn()
            return NodeStmt(assn)
        if self.curr() == Token("rd"):
            rd = self.parseRd()
            return NodeStmt(rd)
        if self.curr() == Token("if"):
            ifE = self.parseIf()
            return NodeStmt(ifE)
        if self.curr() == Token("while"):
            whil = self.parseWhile()
            return NodeStmt(whil)
        if self.curr() == Token("begin"):
            beg = self.parseBeg()
            return NodeStmt(beg)
        if self.curr() == Token("def"):
            dec = self.parseDecl()
            return NodeStmt(dec)
        return None

    def parseBlock(self):
        """ generated source for method parseBlock """
        stmt = self.parseStmt()
        rest = None
        if self.curr() == Token(";"):
            self.match(";")
            rest = self.parseBlock()
        block = NodeBlock(stmt, rest)
        return block

    def parseProg(self):
        block = self.parseBlock()
        prog = NodeProg(block)
        return prog

    def parse(self, program):
        """ generated source for method parse """
        self.scanner = Scanner(program)
        self.scanner.next()
        return self.parseBlock()

