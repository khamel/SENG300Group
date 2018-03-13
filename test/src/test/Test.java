package test;


import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;

public class Test {
	
	
	public static String type;
	public static String inputFilePath;
	
	public static int numDeclarations;
	public static int numReferences;
	
	
	public static void main(String[] args) 
	{
		//TODO
		getUserInput();
		String source = convertSourceToString();
		
		parseSource(source);
		printResults();
	}
	
	public static void getUserInput()
	{
		//TODO - set type in here as well
		type = "A";

	}
	
	public static String convertSourceToString()
	{
		//TODO
		String source = "public class A { int j; \n ArrayList<String> al = null; \n char a = 'a'; \n String n; \n double i;}";
		return source;

	}

	public static void parseSource(String source)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS9);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source.toCharArray());
		
		inputFilePath = "/Users/Sarah/Documents/SENG300";
		
		parser.setUnitName(inputFilePath.toString());
		
		String[] classPath = {""};//source class file location
		String[] sourcePath = {""};//source java file location
		
		parser.setEnvironment(classPath, sourcePath, new String[] {"UTF-8"}, true);
		
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		cu.accept(new ASTVisitor()
			{
			public boolean visit(VariableDeclarationFragment node)
				{
					ITypeBinding binding = node.getName().resolveTypeBinding();
					
					//REMOVE
					System.out.println(binding.getQualifiedName());//
					
					if(type.equals(binding.getQualifiedName()))
					{
						numDeclarations++;
					}
					
					return true;
				}
			
			public boolean visit(TypeDeclaration node) 
				{
					ITypeBinding binding = node.getName().resolveTypeBinding();
					
					//REMOOVE
					System.out.println(binding.getQualifiedName());
					
					if(type.equals(binding.getQualifiedName()))
							{
								numDeclarations++;
							}
					
				
					return true;
				}
			
			public boolean visit(AnnotationTypeDeclaration node) 
			{
				ITypeBinding binding = node.getName().resolveTypeBinding();
				
				//REMOOVE
				System.out.println(binding.getQualifiedName());
				
				if(type.equals(binding.getQualifiedName()))
						{
							numDeclarations++;
						}
				
			
				return true;
			}
			
			public boolean visit(EnumDeclaration node) 
			{
				ITypeBinding binding = node.getName().resolveTypeBinding();
				
				//REMOOVE
				System.out.println(binding.getQualifiedName());
				
				if(type.equals(binding.getQualifiedName()))
						{
							numDeclarations++;
						}
				
			
				return true;
			}
			});
		
		
		return;
	}
	
	public static void printResults()
	{
		//TODO
		
		System.out.println("Number of declarations: " + numDeclarations);
		
		return;
	}

}
