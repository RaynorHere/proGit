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

    def parseAssn(self):
        _id = self.curr()
        self.match('id')
        self.match('=')
        num = self.curr()
        self.match('num')
        return NodeAssn(_id.lexeme, num.lexeme)

    def parseStmt(self):
        assn = self.parseAssn()
        return NodeStmt(assn)

    def parseBlock(self):
        stmt = self.parseStmt()
        if self.curr().lexeme == ';':
            self.match(';')
            block = self.parseBlock()
            return NodeBlock(stmt, block)
        return NodeBlock(stmt, 'None')

    def parse(self, program):
        """ generated source for method parse """
        if program == '': return None
        self.scanner = Scanner(program)
        self.scanner.next()
        return self.parseBlock()

