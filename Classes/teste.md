```plantuml
@startuml
hide circle
left to right direction

'skinparam classAttributeIconSize 0
'skinparam classBackgroundColor LightBlue

class Curso {
  +codigo: String
  +nome: String
}

class ComponenteCurricular {
  +codigo: String
  +nome: String
  +cargaHoraria: Integer
}

class Turma {
  +codigo: String
  +semestre: String
  +ano: Integer
}

class Professor {
  +matricula: String
  +nome: String
}

class Aluno {
  +ra: String
  +nome: String
}

' Relacionamentos e Multiplicidades
Curso "1" --> "1..*" ComponenteCurricular : possui
ComponenteCurricular "1" --> "1..*" Turma : oferece
Professor "1" --> "1..*" Turma : leciona
Aluno "1..*" --> "1..*" Turma : matriculado em

@enduml