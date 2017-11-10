package funcprog.structures

import org.scalatest.{MustMatchers, WordSpec}
import SubstringSearch._

class SubstringSearchTest extends WordSpec with MustMatchers {

  "kmp table" in {
    kmpTable("ABCDABD".toCharArray).toList mustBe List(-1, 0, 0, 0, -1, 0, 2)
    kmpTable("ABACABABC".toCharArray).toList mustBe List(-1, 0, -1, 1, -1, 0, -1, 3, 2)
    kmpTable("PARTICIPATE IN PARACHUTE".toCharArray).toList mustBe List(-1,0,0,0,0,0,0,-1,0,2,0,0,0,0,0,-1,0,0,3,0,0,0,0,0)
  }

  "kmp search" in {
    kmpSearch(UseCase("abcdef".toCharArray, "abc".toCharArray)) mustBe true
    kmpSearch(UseCase("computer".toCharArray, "muter".toCharArray)) mustBe false
    kmpSearch(UseCase("stringmatchingmat".toCharArray, "ingmat".toCharArray)) mustBe true
    kmpSearch(UseCase("videobox".toCharArray, "videobox".toCharArray)) mustBe true
  }

}
